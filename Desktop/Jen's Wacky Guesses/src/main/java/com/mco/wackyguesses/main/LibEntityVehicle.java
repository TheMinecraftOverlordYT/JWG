package com.mco.wackyguesses.main;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class LibEntityVehicle extends EntityLiving implements IJumpingMount, IEntityAdditionalSpawnData {

    /** COMMON METHODS **/

    // Sets the Entity's attributes
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        double maxHealth = 20.0D; // max health (default 20)

        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(3F);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
    }

    public SoundEvent getMoveSound() {
        return null;
    }

    /** OTHER METHODS **/

    protected float jumpPower;
    protected float maxSpeed;
    protected float acceleration;
    protected boolean horseJumping;

    private boolean canJump = false;
    private boolean useJumpingAsShooting = false;

    private boolean groundControl = true;
    private boolean flyingControl = false;
    private boolean boatControl = false;
    private boolean canHover = false;

    private Vec3d playerPosition = new Vec3d(0, 0, 0);

    private List<ParticleEmitter> listParticleEmitters = new ArrayList<>();

    //used to help client fire an onInitialSpawn like method on entity
    private boolean justSpawned = true;

    public float moveForwardLife = 0;

    //private float maxSpeed = 1F;
    //private float acceleration = 0.1F;

    private long lastSoundPlayTime = 0;

    class ParticleEmitter {
        private Vec3d particlePos = null;
        @SideOnly(Side.CLIENT)
        private EnumParticleTypes particle;
        private int particlesPerTick = 5;
        private float particleArea = 0.5F;

        public ParticleEmitter(Vec3d particlePos, EnumParticleTypes particle, int particlesPerTick,
                               float particleArea) {
            super();
            this.particlePos = particlePos;
            this.particle = particle;
            this.particlesPerTick = particlesPerTick;
            this.particleArea = particleArea;
        }
    }

    public LibEntityVehicle(World worldIn) {
        super(worldIn);
        this.isImmuneToFire = true;

        this.setSize(1F, 0.75F);

        this.setGroundVehicle();

        this.stepHeight = 1F;

        this.enablePersistence();
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

//    public float getAcceleration() {
//        return acceleration;
//    }
//
//    public void setAcceleration(float acceleration) {
//        this.acceleration = acceleration;
//    }

    public boolean isGroundControl() {
        return groundControl;
    }

    public boolean isFlyingControl() {
        return flyingControl;
    }

    public boolean isBoatControl() {
        return boatControl;
    }

    /**
     * Set a type of particle to generate every tick, position relative to entity, which is then rotated correctly
     *
     * @param x positive x is right, negative x is left
     * @param y positive y is up, negative y is down
     * @param z positive z is back, negative z is front
     * @param particle
     * @param particlesPerTick
     * @param particleArea Circular area radius to spread particles over
     */
    public void addParticles(float x, float y, float z, EnumParticleTypes particle, int particlesPerTick, float particleArea) {
        if (worldObj.isRemote) {
            addParticlesImpl(x, y, z, particle, particlesPerTick, particleArea);
        }
    }

    /**
     * Set relative position for passenger, which is then rotated correctly
     *
     * @param x positive x is right, negative x is left
     * @param y positive y is up, negative y is down
     * @param z positive z is back, negative z is front
     */
    public void setRelativePassengerPosition(float x, float y, float z) {
        playerPosition = new Vec3d(x, y, z);
    }

    @SideOnly(Side.CLIENT)
    private void addParticlesImpl(float x, float y, float z, EnumParticleTypes particle, int particlesPerTick, float particleArea) {
        listParticleEmitters.add(new ParticleEmitter(new Vec3d(x, y, z), particle, particlesPerTick, particleArea));
    }

    public void canJump(boolean canJump) {
        this.canJump = canJump;
    }

    public boolean isCanHover() {
        return canHover;
    }

    public void setCanHover(boolean canHover) {
        this.canHover = canHover;
    }

    /**
     * Set vehicle to ground controlled type
     */
    public void setGroundVehicle() {
        flyingControl = false;
        groundControl = true;
        boatControl = false;
        jumpMovementFactor = 0.02F;
    }

    /**
     * Set vehicle to air flying type
     */
    public void setAirVehicle() {
        flyingControl = true;
        boatControl = false;
        groundControl = false;
        jumpMovementFactor = 0.0F;
    }

    /**
     * Set vehicle to water controlled type
     */
    public void setWaterVehicle() {
        flyingControl = false;
        boatControl = true;
        groundControl = false;
        jumpMovementFactor = 0.0F;
    }

    @Override
    @Nullable
    public Entity getControllingPassenger()
    {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public boolean canBeSteered() {
        Entity entity = this.getControllingPassenger();
        if (entity instanceof EntityPlayer) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canPassengerSteer() {
        return canBeSteered();
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (hand == EnumHand.MAIN_HAND) {
            onInteract(player);
        }
        if (!isBeingRidden()) {
            if (!player.worldObj.isRemote) {
                player.startRiding(this);
            }
            return true;
        }
        return false;
    }

    //extend AbstractHorse and override me to be controllable, unless we override travel instead
    /*@Override
    public boolean isHorseSaddled() {
        return true;
    }*/

    @Override
    public void setJumpPower(int jumpPowerIn) {
        if (jumpPowerIn < 0)
        {
            jumpPowerIn = 0;
        }

        if (jumpPowerIn >= 90)
        {
            this.jumpPower = 1.0F;
        }
        else
        {
            this.jumpPower = 0.4F + 0.4F * (float)jumpPowerIn / 90.0F;
        }
    }

    @Override
    public boolean canJump() {
        return canJump;
    }

    @Override
    public void handleStartJump(int p_184775_1_) {

    }

    @Override
    public void handleStopJump() {

    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Override
    public boolean shouldRiderFaceForward(EntityPlayer player) {
        return super.shouldRiderFaceForward(player);
    }

    @Override
    public void updatePassenger(Entity passenger)
    {
        if (this.isPassenger(passenger))
        {
            Vec3d pos = playerPosition;

            //fix for relative riding rotation issue
            float yaw = this.renderYawOffset;
            //yaw = this.rotationYaw;

            //inverting x and z to make it correct to known standards
            double relZAdjX = -Math.sin(Math.toRadians(yaw - 0F) - (float)Math.PI) * pos.zCoord;
            double relZAdjZ = Math.cos(Math.toRadians(yaw - 0F) - (float)Math.PI) * pos.zCoord;
            double relXAdjX = -Math.sin(Math.toRadians(yaw - 90F) - (float)Math.PI) * pos.xCoord;
            double relXAdjZ = Math.cos(Math.toRadians(yaw - 90F) - (float)Math.PI) * pos.xCoord;

            passenger.setPosition(this.posX + relZAdjX + relXAdjX,
                    this.posY + pos.yCoord,
                    this.posZ + relZAdjZ + relXAdjZ);
        }
    }

    @Override
    public double getMountedYOffset()
    {
        return (double)this.height * 0.75D;
    }

    protected float getWaterSlowDown()
    {
        return 0.88F;
    }

    /**
     * Moves the entity based on the specified heading.
     */
    @Override
    public void moveEntityWithHeading(float strafe, float forward)
    {
        if (this.isBeingRidden() && this.canBeSteered())
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
            this.rotationYaw = entitylivingbase.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.renderYawOffset = this.rotationYaw;
            this.rotationYawHead = this.renderYawOffset;
            strafe = entitylivingbase.moveStrafing * 0.5F;
            forward = entitylivingbase.moveForward * 100;

            if (forward <= 0.0F)
            {
                forward *= 0.25F;
            }

            if (this.onGround && this.jumpPower == 0.0F)
            {
                strafe *= 15F;
                forward *= .01F;
            }

            if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround)
            {
                this.motionY = this.getHorseJumpStrength() * (double)this.jumpPower;

                if (this.isPotionActive(MobEffects.JUMP_BOOST))
                {
                    this.motionY += (double)((float)(this.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
                }

                this.setHorseJumping(true);
                this.isAirBorne = true;

                if (forward > 0.0F)
                {
                    float f = MathHelper.sin(this.rotationYaw * 0.017453292F);
                    float f1 = MathHelper.cos(this.rotationYaw * 0.017453292F);
                    this.motionX += (double)(-0.4F * f * this.jumpPower)-1;
                    this.motionZ += (double)(0.4F * f1 * this.jumpPower)-1;
                    this.playSound(SoundEvents.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
                }

                this.jumpPower = 0.0F;
                net.minecraftforge.common.ForgeHooks.onLivingJump(this);
            }

            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            if (this.canPassengerSteer() && this.getControllingPassenger() != null)
            {
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() - 2.7F);
                super.moveEntityWithHeading(strafe, forward*2);
            }
            else if (entitylivingbase instanceof EntityPlayer)
            {
                this.motionX = 0.0D;
                this.motionY = 0.0D;
                this.motionZ = 0.0D;
            }

            if (this.onGround)
            {
                this.jumpPower = 0.0F;
                this.setHorseJumping(false);
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.posX - this.prevPosX;
            double d0 = this.posZ - this.prevPosZ;
            float f2 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

            if (f2 > 1.0F)
            {
                f2 = 1.0F;
            }

            this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        }
        else
        {
            this.jumpMovementFactor = 0.02F;
            super.moveEntityWithHeading(strafe, forward);
        }
    }


    //for WASD movement override, either override travel, or extend AbstractHorse and delete this method

    public void travel(float strafe, float up, float forward)
    {
        double curSpeed = Math.sqrt(motionX * motionX + motionZ * motionZ + motionY * motionY);
        double motion = Math.sqrt(motionX * motionX + motionZ * motionZ);
        //drop when no forward momentum
        if (flyingControl) {
            if (!canHover || !isBeingRidden()) {


                if (motion < 0.8F) {
                    motionY -= 0.008F;
                }

                //motionY -= Math.max(0.02D - Math.abs(motion / 2D), 0);
            } else {
                motionY -= 0.002F;
            }
        }

        if (this.isBeingRidden() && this.canBeSteered()/* && this.isHorseSaddled()*/)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
            this.rotationYaw = entitylivingbase.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.renderYawOffset = this.rotationYaw;
            this.rotationYawHead = this.renderYawOffset;
            strafe = entitylivingbase.moveStrafing * 0.5F;
            forward = entitylivingbase.moveForward * 10;

            //limit vehicle movement
            strafe = 0;

            //add pitch aim based motionY
            if (flyingControl) {
                up = (float)Math.sin(Math.toRadians(this.rotationPitch + 180)) * forward * 4F;


            }

            if (boatControl && this.onGround) {
                forward *= 0.05F;
                strafe *= 0.05F;
            }


            if (forward <= 0.0F)
            {
                forward *= 0.25F;

                //this.gallopTime = 0;
            }

            if (this.onGround && this.jumpPower == 0.0F/* && this.isRearing() && !this.allowStandSliding*/)
            {
                /*p_191986_1_ = 0.0F;
                p_191986_3_ = 0.0F;*/
            }

            if (!useJumpingAsShooting) {
                if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround) {

                    this.motionY = this.getHorseJumpStrength() * (double) this.jumpPower;

                    if (this.isPotionActive(MobEffects.JUMP_BOOST)) {
                        this.motionY += (double) ((float) (this.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
                    }

                    this.setHorseJumping(true);
                    this.isAirBorne = true;

                    if (forward > 0.0F) {
                        float f = MathHelper.sin(this.rotationYaw * 0.017453292F);
                        float f1 = MathHelper.cos(this.rotationYaw * 0.017453292F);
                        this.motionX += (double) (-0.4F * f * this.jumpPower);
                        this.motionZ += (double) (0.4F * f1 * this.jumpPower);
                        //this.playSound(SoundEvents.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
                    }

                    this.jumpPower = 0.0F;
                }
            } else {

                if (this.jumpPower > 0.0F && !this.isHorseJumping()) {

                    /*this.motionY = this.getHorseJumpStrength() * (double) this.jumpPower;

                    if (this.isPotionActive(MobEffects.JUMP_BOOST)) {
                        this.motionY += (double) ((float) (this.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
                    }*/

                    //this.setHorseJumping(true);
                    //this.isAirBorne = true;

                    /*if (forward > 0.0F) {
                        float f = MathHelper.sin(this.rotationYaw * 0.017453292F);
                        float f1 = MathHelper.cos(this.rotationYaw * 0.017453292F);
                        this.motionX += (double) (-0.4F * f * this.jumpPower);
                        this.motionZ += (double) (0.4F * f1 * this.jumpPower);
                        //this.playSound(SoundEvents.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
                    }*/

                    this.jumpPower = 0.0F;
                }
            }

            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            if (this.canPassengerSteer())
            {
                this.setAIMoveSpeed(this.getVehicleSpeed()/*float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()*/);
                moveForward = forward;

                if (boatControl && this.isInWater()) {
                    this.motionX /= 0.91F;
                    this.motionZ /= 0.91F;
                }

                if (!boatControl) {
                    this.travel(strafe, up, forward*10);
                    //undo vanilla drag
                    this.motionX /= (double)0.91F;
                    this.motionZ /= (double)0.91F;
                } else {
                    if (curSpeed < getVehicleSpeed() * 1.2F) {
                        this.travel(strafe, up, forward+10);
                        //undo vanilla drag
                        this.motionX /= (double)0.91F;
                        this.motionZ /= (double)0.91F;
                    } else {
                        this.travel(strafe, up, 0);
                    }
                }
                /*if (curSpeed < maxSpeed) {
                    super.travel(strafe, up, forward);
                } else {
                    super.travel(strafe, up, 0);
                }*/

                if (motion > 0.1F) {//if (moveForward != 0) {
                    if (lastSoundPlayTime + getSoundRate() < worldObj.getTotalWorldTime()) {
                        lastSoundPlayTime = worldObj.getTotalWorldTime();
                        this.playSound(getMoveSound(), this.getSoundVolume() * 1.0F, 1F);
                    }
                }

                //System.out.println("curSpeed: " + curSpeed);



                if (!boatControl) {
                    //use our drag
                    this.motionX *= (double)1.98F;
                    this.motionZ *= (double)1.98F;
                } else {
                    //use our drag
                    this.motionX *= (double)1.93F;
                    this.motionZ *= (double)1.93F;
                }

            }
            else if (entitylivingbase instanceof EntityPlayer)
            {
                this.motionX = 0.0D;
                this.motionY = 0.0D;
                this.motionZ = 0.0D;
            }

            if (!useJumpingAsShooting) {
                if (this.onGround) {
                    this.jumpPower = 0.0F;
                    this.setHorseJumping(false);
                }
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.posX - this.prevPosX;
            double d0 = this.posZ - this.prevPosZ;
            float f2 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

            if (f2 > 1.0F)
            {
                f2 = 1.0F;
            }

            this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        }
        else
        {
            this.jumpMovementFactor = 0.02F;
            moveForward = forward*10;
            this.travel(strafe, up, forward);
        }

        moveForwardLife += moveForward;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        this.fallDistance = 0;

        if (boatControl) {
            if (this.isInWater()) {
                this.motionY = 0.01F;
            }
        }
        this.setAir(20);
    }

    public boolean isHorseJumping()
    {
        return this.horseJumping;
    }

    public void setHorseJumping(boolean jumping)
    {
        this.horseJumping = jumping;
    }

    public double getHorseJumpStrength()
    {
        return 2.5D;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeFloat(rotationYaw);
        buffer.writeFloat(rotationYawHead);
        buffer.writeBoolean(justSpawned);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        rotationYaw = additionalData.readFloat();
        rotationYawHead = additionalData.readFloat();
        justSpawned = additionalData.readBoolean();
        if (justSpawned) {
            onFirstSpawn();
        }
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        this.rotationYaw = 0;
        this.rotationYawHead = 0;
        onFirstSpawn();
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public void onFirstSpawn() {

    }

    @Override
    public void onUpdate() {
        justSpawned = false;
        super.onUpdate();
        if (worldObj.isRemote) {
            tickClient();
        }

    }

    @SideOnly(Side.CLIENT)
    public void tickClient() {

        double motion = Math.sqrt(motionX * motionX + motionZ * motionZ);

        if (getPassengers().size() > 0 && motion > 0.1F) {

            for (ParticleEmitter emitter : listParticleEmitters) {
                Vec3d pos = emitter.particlePos;

                //inverting x and z to make it correct to known standards
                double relZAdjX = -Math.sin(Math.toRadians(this.rotationYaw - 0F) - (float)Math.PI) * pos.zCoord;
                double relZAdjZ = Math.cos(Math.toRadians(this.rotationYaw - 0F) - (float)Math.PI) * pos.zCoord;
                double relXAdjX = -Math.sin(Math.toRadians(this.rotationYaw - 90F) - (float)Math.PI) * pos.xCoord;
                double relXAdjZ = Math.cos(Math.toRadians(this.rotationYaw - 90F) - (float)Math.PI) * pos.xCoord;

                for (int i = 0; i < emitter.particlesPerTick; i++) {
                    double randX = (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * emitter.particleArea;
                    double randY = (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * emitter.particleArea;
                    double randZ = (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * emitter.particleArea;

                    this.worldObj.spawnParticle(emitter.particle, this.posX + relZAdjX + relXAdjX + randX,
                            this.posY + pos.yCoord + randY,
                            this.posZ + relZAdjZ + relXAdjZ + randZ, 0, 0, 0);
                }
            }
        }


    }

    /**
     * Get the vehicle speed
     *
     * @return
     */
    public abstract float getVehicleSpeed();

    public int getSoundRate() {
        return 0;
    }

    public void onInteract(EntityPlayer player)
    {
    }
}

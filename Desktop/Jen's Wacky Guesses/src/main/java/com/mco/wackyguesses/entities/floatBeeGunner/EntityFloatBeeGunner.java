package com.mco.wackyguesses.entities.floatBeeGunner;

import com.mco.wackyguesses.entities.floatBeeGunner.bee.EntityBee;
import com.mco.wackyguesses.entities.floatBeeGunner.bullet.EntityBullet;
import com.mco.wackyguesses.main.WackyDamageSources;
import com.mco.wackyguesses.main.WackySoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import java.util.List;

public class EntityFloatBeeGunner extends EntityMob implements IRangedAttackMob
{
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);

    public EntityFloatBeeGunner(World worldIn)
    {
        super(worldIn);
        this.setHealth(this.getMaxHealth());
        this.setSize(1.2F, 1.7F);
        ((PathNavigateGround)this.getNavigator()).setCanSwim(true);
        this.experienceValue = 75;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25D, 1, 20.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
    }

    protected void updateAITasks()
    {
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }


    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount) && source != WackyDamageSources.bullet;
    }

    public void onLivingUpdate()
    {
        if(getAttackTarget()==null)
        {
            List<EntityPlayer> list = this.worldObj.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expandXyz(32.0D));
            for(EntityPlayer entity : list)
            {
                if(entity!=null)
                    setAttackTarget(entity);
            }
        }

        this.motionY *= 0.8000000238418579D;

        if (!this.worldObj.isRemote && this.getAttackTarget() != null)
        {
            Entity entity = this.getAttackTarget();

            if (entity != null)
            {
                if (this.posY < entity.posY )
                {
                    if (this.motionY < 0.0D)
                    {
                        this.motionY = 0.0D;
                    }

                    this.motionY += (0.5D - this.motionY) * 0.6000000238418579D;
                }

                if(this.posY < entity.posY + 2)
                    this.motionY += .1;
                else if(this.posY > entity.posY + 2)
                    this.motionY -= .1;
                else
                    this.motionY = 0;

                double d0 = entity.posX - this.posX + 2;
                double d1 = entity.posZ - this.posZ + 2;
                double d3 = d0 * d0 + d1 * d1;

                if (d3 > 9.0D)
                {
                    double d5 = (double) MathHelper.sqrt_double(d3);
                    this.motionX += (d0 / d5 * 0.5D - this.motionX) * 0.6000000238418579D;
                    this.motionZ += (d1 / d5 * 0.5D - this.motionZ) * 0.6000000238418579D;
                }
            }
        }

        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 0.05000000074505806D)
        {
            this.rotationYaw = (float)MathHelper.atan2(this.motionZ, this.motionX) * (180F / (float)Math.PI) - 90.0F;
        }

        super.onLivingUpdate();
    }

    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
        EntityBee entityBee = new EntityBee(worldObj);
        EntityBee entityBee1 = new EntityBee(worldObj);
        EntityBee entityBee2 = new EntityBee(worldObj);
        if(!this.worldObj.isRemote)
        {
            entityBee.setPosition(this.posX, this.posY, this.posZ);
            entityBee1.setPosition(this.posX, this.posY, this.posZ);
            entityBee2.setPosition(this.posX, this.posY, this.posZ);
            this.worldObj.spawnEntityInWorld(entityBee);
            this.worldObj.spawnEntityInWorld(entityBee1);
            this.worldObj.spawnEntityInWorld(entityBee2);
        }
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase target, float p_82196_2_)
    {
        EntityBullet entityBullet = new EntityBullet(this.worldObj, this);
        double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
        double d1 = target.posX - this.posX;
        double d2 = d0 - entityBullet.posY;
        double d3 = target.posZ - this.posZ;
        float f = MathHelper.sqrt_double(d1 * d1 + d3 * d3) * 0.2F;
        entityBullet.setThrowableHeading(d1, d2 + (double)f, d3, 1.6F, 7.0F);
        if(ticksExisted % 10 == 0)
            this.playSound(WackySoundHandler.MINIGUN, .5F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityBullet);
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

}

package com.mco.wackyguesses.entities.dorito;

import com.mco.wackyguesses.entities.dorito.doritoThrowable.EntityDoritoThrowable;
import com.mco.wackyguesses.main.WackyLootTables;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import java.util.Random;

public class EntityDorito extends EntityMob
{
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);

    public EntityDorito(World worldIn)
    {
        super(worldIn);
        this.setSize(2F, 2F);
        this.experienceValue = 50;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(1, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 32F));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(.4);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(75);
    }

    protected boolean isAIEnabled()
    {
        return true;
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

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        Random rand = new Random();
        if(rand.nextInt(25) == 0 && this.getAttackTarget() != null)
        {
            attackEntityWithRangedAttack(this.getAttackTarget(), 1);
        }

        if(rand.nextInt(150) == 0 && this.getAttackTarget() != null)
        for (int i = 0; i < 360; i += 7.5)
        {
            float speed = 2F;
            EntityDoritoThrowable doritoThrowable = new EntityDoritoThrowable(worldObj);
            doritoThrowable.motionX = Math.sin((float)Math.toRadians(i)) * speed / 8;
            doritoThrowable.motionZ = Math.cos((float)Math.toRadians(i)) * speed / 8;
            doritoThrowable.motionY = 0F;
            doritoThrowable.setPosition(posX, posY + 2, posZ);
            worldObj.spawnEntityInWorld(doritoThrowable);
        }
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase target, float p_82196_2_)
    {
        EntityDoritoThrowable entityDoritoThrowable = new EntityDoritoThrowable(this.worldObj, this);
        double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
        double d1 = target.posX - this.posX;
        double d2 = d0 - entityDoritoThrowable.posY;
        double d3 = target.posZ - this.posZ;
        float f = MathHelper.sqrt_double(d1 * d1 + d3 * d3) * 0.2F;
        entityDoritoThrowable.setThrowableHeading(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityDoritoThrowable);
    }

    @Override
    public ResourceLocation getLootTable()
    {
        return WackyLootTables.DORITO;
    }

}

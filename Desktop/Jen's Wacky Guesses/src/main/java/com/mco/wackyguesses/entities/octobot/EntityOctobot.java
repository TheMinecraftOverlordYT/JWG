package com.mco.wackyguesses.entities.octobot;

import com.mco.wackyguesses.main.WackyDamageSources;
import com.mco.wackyguesses.main.WackySoundHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityOctobot extends EntityMob {
    private boolean shouldRenderLasers = false;
    private int chargeTicks;
    private int chargeBonus;
    private boolean shouldRandomize = true;

    public EntityOctobot(World worldIn) {
        super(worldIn);
        this.setSize(2F, 3F);
        this.experienceValue = 50;
    }

    protected void initEntityAI() {
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
    }


    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(.2);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(75);
    }

    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (getAttackTarget() == null) {
            List<EntityPlayer> list = this.worldObj.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expandXyz(32.0D));
            for (EntityPlayer entity : list) {
                if (entity != null)
                    setAttackTarget(entity);
            }
        }

        if(this.ticksExisted % 50 == 0 && shouldRandomize)
        {
            Random random = new Random();
            chargeBonus = random.nextInt(50);
            shouldRandomize = false;
        }

        if (this.getAttackTarget() != null && (this.getAttackTarget() instanceof EntityPlayerSP || this.getAttackTarget() instanceof EntityPlayerMP))
        {
            chargeTicks++;
            if(getAttackTarget() instanceof EntityPlayer)
            {
               if(chargeTicks > 10 && chargeTicks < 101)
               {
                   if(chargeTicks == 11)
                   {
                        this.playSound(WackySoundHandler.LASER, 1F, 1F);
                   }

                   if(chargeTicks == 55)
                   {
                       getAttackTarget().attackEntityFrom(WackyDamageSources.octobot, 10 + (chargeBonus/10));
                       shouldRenderLasers = true;
                   }
               }
           }

           if(chargeTicks > 56)
           {
               shouldRenderLasers = false;
           }

           if(chargeTicks > 101)
           {
               chargeTicks = 0;
           }
        }
    }

    public boolean shouldRenderLasers()
    {
        return shouldRenderLasers;
    }

    public float getEyeHeight()
    {
        return 1.25F;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    public ResourceLocation getLootTable()
    {
        return null;
    //    return WackyLootTables.SPHERICAL_CHOCO;
    }
}

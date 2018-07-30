package com.mco.wackyguesses.entities.egg;

import com.mco.wackyguesses.main.WackyLootTables;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public class EntityEgg extends EntityMob
{
    public EntityEgg(World worldIn)
    {
        super(worldIn);
        this.setSize(4F, 6F);
        this.experienceValue = 10;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
    }


    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(.4);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
    }

    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if(getAttackTarget()==null)
        {
            List<EntityPlayer> list = this.worldObj.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expandXyz(32.0D));
            for(EntityPlayer entity : list)
            {
                if(entity!=null)
                    setAttackTarget(entity);
            }
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    public ResourceLocation getLootTable()
    {
        return WackyLootTables.EGG;
    }
}

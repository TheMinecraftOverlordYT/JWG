package com.mco.wackyguesses.entities.angelicSpider;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;

public class EntityAngelicSpider extends EntitySpider
{
    public EntityAngelicSpider(World worldIn)
    {
        super(worldIn);
        this.setSize(1.4F, 0.9F);
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        tasks.addTask(0, new EntityAILeapAtTarget(this, .8F));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
    }

    public void onUpdate()
    {
        super.onUpdate();
        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.8D;
        }
    }

    public void fall(float distance, float damageMultiplier)
    {
    }


}

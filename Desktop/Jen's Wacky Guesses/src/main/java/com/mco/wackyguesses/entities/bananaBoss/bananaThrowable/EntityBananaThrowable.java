package com.mco.wackyguesses.entities.bananaBoss.bananaThrowable;

import com.mco.wackyguesses.entities.bananaBoss.EntityBanana;
import com.mco.wackyguesses.main.WackyDamageSources;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBananaThrowable extends EntityThrowable
{
    public EntityBananaThrowable(World worldIn)
    {
        super(worldIn);
    }

    public EntityBananaThrowable(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityBananaThrowable(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void func_189662_a(DataFixer p_189662_0_)
    {
        EntityThrowable.func_189661_a(p_189662_0_, "Banana");
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            int i = 1;

            if(!(result.entityHit instanceof EntityBanana))
                result.entityHit.attackEntityFrom(WackyDamageSources.banana, (float)i);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}

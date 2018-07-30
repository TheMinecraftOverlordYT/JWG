package com.mco.wackyguesses.entities.dorito.doritoThrowable;

import com.mco.wackyguesses.entities.dorito.EntityDorito;
import com.mco.wackyguesses.main.WackyDamageSources;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDoritoThrowable extends EntityThrowable
{
    public EntityDoritoThrowable(World worldIn)
    {
        super(worldIn);
    }

    public EntityDoritoThrowable(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityDoritoThrowable(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void func_189662_a(DataFixer p_189662_0_)
    {
        EntityThrowable.func_189661_a(p_189662_0_, "Dorito");
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            int i = 1;

            if(!(result.entityHit instanceof EntityDorito))
                result.entityHit.attackEntityFrom(WackyDamageSources.dorito, (float)i);
        }

        for (int j = 0; j < 8; ++j)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }

    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity()
    {
        return 0.01F;
    }

}

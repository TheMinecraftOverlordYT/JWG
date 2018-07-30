package com.mco.wackyguesses.main;

import net.minecraft.util.DamageSource;

public class WackyDamageSources extends DamageSource
{
    public WackyDamageSources(String name)
    {
        super(name);
    }

    public static DamageSource octobot = new WackyDamageSources("octobot");
    public static DamageSource chocolate = new WackyDamageSources("chocolate");
    public static DamageSource banana = new WackyDamageSources("banana");
    public static DamageSource dorito = new WackyDamageSources("dorito");
    public static DamageSource bullet = new WackyDamageSources("bullet");

//    public static DamageSource smallWeight(EntitySmallWeight entitySmallWeight, EntityLivingBase thrower)
//    {
//        return (new EntityDamageSourceIndirect("smallWeight", entitySmallWeight, thrower)).setProjectile();
//    }

}

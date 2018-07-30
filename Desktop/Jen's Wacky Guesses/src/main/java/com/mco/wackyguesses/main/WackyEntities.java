package com.mco.wackyguesses.main;

import com.mco.wackyguesses.entities.angelicSpider.EntityAngelicSpider;
import com.mco.wackyguesses.entities.bananaBoss.EntityBanana;
import com.mco.wackyguesses.entities.bananaBoss.bananaSmall.EntityBananaMinion;
import com.mco.wackyguesses.entities.bananaBoss.bananaThrowable.EntityBananaThrowable;
import com.mco.wackyguesses.entities.bananamobile.EntityBananamobile;
import com.mco.wackyguesses.entities.chocolate.box.EntityChocoBox;
import com.mco.wackyguesses.entities.chocolate.rectangle.EntityRectangularChoco;
import com.mco.wackyguesses.entities.chocolate.sphere.EntitySphericalChoco;
import com.mco.wackyguesses.entities.dorito.EntityDorito;
import com.mco.wackyguesses.entities.dorito.doritoThrowable.EntityDoritoThrowable;
import com.mco.wackyguesses.entities.egg.EntityEgg;
import com.mco.wackyguesses.entities.floatBeeGunner.EntityFloatBeeGunner;
import com.mco.wackyguesses.entities.floatBeeGunner.bee.EntityBee;
import com.mco.wackyguesses.entities.floatBeeGunner.bullet.EntityBullet;
import com.mco.wackyguesses.entities.ironPapa.EntityIronPapa;
import com.mco.wackyguesses.entities.octobot.EntityOctobot;
import com.mco.wackyguesses.entities.patHead.EntityPatHead;

public class WackyEntities
{
    private static final WackyEntities INSTANCE = new WackyEntities();

    public static WackyEntities instance()
    {
        return INSTANCE;
    }

    public static void registerEntity()
    {
        LibEntityRegistry.registerEntity("banana_boss", EntityBanana.class,  0xFFFF00, 0x302013);
        LibEntityRegistry.registerEntity("banana_minion", EntityBananaMinion.class,  0x302013, 0xFFFF00);
        LibEntityRegistry.registerEntityEggless("banana_throwable", EntityBananaThrowable.class);
        LibEntityRegistry.registerEntityEggless("bananamobile", EntityBananamobile.class);

        LibEntityRegistry.registerEntity("angelic_spider", EntityAngelicSpider.class,  0xfff587, 0xfffded);

        LibEntityRegistry.registerEntity("dorito", EntityDorito.class, 0xff3200, 0xff6a00);
        LibEntityRegistry.registerEntityEggless("dorito_throwable", EntityDoritoThrowable.class);

        LibEntityRegistry.registerEntity("pat_head", EntityPatHead.class, 0x03193d, 0x000000);

        LibEntityRegistry.registerEntity("choco_box", EntityChocoBox.class, 0xe2b900, 0x1c1701);
        LibEntityRegistry.registerEntityEggless("rectangular_choco", EntityRectangularChoco.class);
        LibEntityRegistry.registerEntityEggless("spherical_choco", EntitySphericalChoco.class);

        LibEntityRegistry.registerEntity("egg", EntityEgg.class, 0xffee00, 0xffffff);

        LibEntityRegistry.registerEntity("octobot", EntityOctobot.class, 0xb5b8bc, 0x1657ad);

        LibEntityRegistry.registerEntity("iron_papa", EntityIronPapa.class, 0x00000, 0x00000e);

        LibEntityRegistry.registerEntity("float_bee_gunner", EntityFloatBeeGunner.class, 0xffee00, 0x000000);
        LibEntityRegistry.registerEntityEggless("bullet", EntityBullet.class);
        LibEntityRegistry.registerEntity("bee", EntityBee.class, 0x000000, 0xffee00);
    }


}

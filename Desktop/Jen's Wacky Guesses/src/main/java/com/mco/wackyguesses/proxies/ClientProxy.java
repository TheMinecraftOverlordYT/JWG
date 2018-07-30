package com.mco.wackyguesses.proxies;

import com.mco.wackyguesses.WackyGuesses;
import com.mco.wackyguesses.entities.angelicSpider.EntityAngelicSpider;
import com.mco.wackyguesses.entities.angelicSpider.RenderAngelicSpider;
import com.mco.wackyguesses.entities.bananaBoss.EntityBanana;
import com.mco.wackyguesses.entities.bananaBoss.RenderBanana;
import com.mco.wackyguesses.entities.bananaBoss.bananaSmall.EntityBananaMinion;
import com.mco.wackyguesses.entities.bananaBoss.bananaSmall.RenderBananaMinion;
import com.mco.wackyguesses.entities.bananaBoss.bananaThrowable.EntityBananaThrowable;
import com.mco.wackyguesses.entities.bananamobile.EntityBananamobile;
import com.mco.wackyguesses.entities.bananamobile.RenderBananamobile;
import com.mco.wackyguesses.entities.chocolate.box.EntityChocoBox;
import com.mco.wackyguesses.entities.chocolate.box.RenderChocoBox;
import com.mco.wackyguesses.entities.chocolate.rectangle.EntityRectangularChoco;
import com.mco.wackyguesses.entities.chocolate.rectangle.RenderRectangularChoco;
import com.mco.wackyguesses.entities.chocolate.sphere.EntitySphericalChoco;
import com.mco.wackyguesses.entities.chocolate.sphere.RenderSphericalChoco;
import com.mco.wackyguesses.entities.dorito.EntityDorito;
import com.mco.wackyguesses.entities.dorito.RenderDorito;
import com.mco.wackyguesses.entities.dorito.doritoThrowable.EntityDoritoThrowable;
import com.mco.wackyguesses.entities.egg.EntityEgg;
import com.mco.wackyguesses.entities.egg.RenderEgg;
import com.mco.wackyguesses.entities.floatBeeGunner.EntityFloatBeeGunner;
import com.mco.wackyguesses.entities.floatBeeGunner.RenderFloatBeeGunner;
import com.mco.wackyguesses.entities.floatBeeGunner.bee.EntityBee;
import com.mco.wackyguesses.entities.floatBeeGunner.bee.RenderBee;
import com.mco.wackyguesses.entities.floatBeeGunner.bullet.EntityBullet;
import com.mco.wackyguesses.entities.ironPapa.EntityIronPapa;
import com.mco.wackyguesses.entities.ironPapa.RenderIronPapa;
import com.mco.wackyguesses.entities.octobot.EntityOctobot;
import com.mco.wackyguesses.entities.octobot.RenderOctobot;
import com.mco.wackyguesses.entities.patHead.EntityPatHead;
import com.mco.wackyguesses.entities.patHead.RenderPatHead;
import com.mco.wackyguesses.main.WackyItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);

        OBJLoader.INSTANCE.addDomain(WackyGuesses.MODID);
        RenderingRegistry.registerEntityRenderingHandler(EntityBanana.class, RenderBanana.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBananaMinion.class, RenderBananaMinion.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBananamobile.class, RenderBananamobile.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityAngelicSpider.class, RenderAngelicSpider.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityDorito.class, RenderDorito.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPatHead.class, RenderPatHead.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityChocoBox.class, RenderChocoBox.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRectangularChoco.class, RenderRectangularChoco.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySphericalChoco.class, RenderSphericalChoco.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityEgg.class, RenderEgg.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityOctobot.class, RenderOctobot.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityIronPapa.class, RenderIronPapa.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityFloatBeeGunner.class, RenderFloatBeeGunner.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, RenderBee.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityBananaThrowable.class, new IRenderFactory() {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderSnowball(manager, WackyItems.itemBanana, Minecraft.getMinecraft().getRenderItem());
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityDoritoThrowable.class, new IRenderFactory() {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderSnowball(manager, WackyItems.itemDorito, Minecraft.getMinecraft().getRenderItem());
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new IRenderFactory() {
        @Override
        public Render createRenderFor(RenderManager manager)
        {
            return new RenderSnowball(manager, WackyItems.itemBullet, Minecraft.getMinecraft().getRenderItem());
        }
    });

    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        WackyItems.registerRenders();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}

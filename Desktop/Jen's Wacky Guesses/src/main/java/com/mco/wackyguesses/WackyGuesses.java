package com.mco.wackyguesses;

import com.mco.wackyguesses.main.WackyEntities;
import com.mco.wackyguesses.main.WackyItems;
import com.mco.wackyguesses.main.WackySoundHandler;
import com.mco.wackyguesses.proxies.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.mco.wackyguesses.WackyGuesses.NAME;

@Mod(modid = WackyGuesses.MODID, version = WackyGuesses.VERSION, name = NAME)
public class WackyGuesses
{
    public static final String MODID = "wackyguesses";
    public static final String VERSION = "1.0";
    public static final String NAME = "Jen's Wacky Guesses";

    @SidedProxy(clientSide = "com.mco.wackyguesses.proxies.ClientProxy", serverSide = "com.mco.wackyguesses.proxies.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static WackyGuesses instance;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        WackyItems.preInit();

        WackyEntities.registerEntity();
        proxy.preInit(event);
    }

    public static CreativeTabs wackyTab = new CreativeTabs("wacky_tab"){
        @Override
        public Item getTabIconItem(){
            return WackyItems.itemBanana;
        }
    };

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        WackySoundHandler.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }


}

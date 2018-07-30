package com.mco.wackyguesses.main;

import com.mco.wackyguesses.WackyGuesses;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class WackyBlocks
{
 //   public static Block itemBanana;

    public static void preInit()
    {
      //  itemBanana = new ItemBanana(3, 4, false).setUnlocalizedName("item_banana").setCreativeTab(WackyGuesses.wackyTab);

        registerItems();
    }

    public static void registerItems()
    {
   //     GameRegistry.register(itemBanana, new ResourceLocation(WackyGuesses.MODID, "item_banana"));
    }

    public static void registerRenders()
    {
   //     registerRender(itemBanana);
    }

    public static void registerRender(Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation(WackyGuesses.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}

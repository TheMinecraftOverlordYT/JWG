package com.mco.wackyguesses.main;

import com.mco.wackyguesses.WackyGuesses;
import com.mco.wackyguesses.items.ItemBananamobile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WackyItems
{
    public static Item itemBanana;
    public static Item itemBananamobile;
    public static Item itemDorito;
    public static Item itemRecChoc;
    public static Item itemSphChoc;
    public static Item itemEgg;
    public static Item itemBullet;

    public static void preInit()
    {
        itemBanana = new ItemFood(4, 4, false).setUnlocalizedName("item_banana").setCreativeTab(WackyGuesses.wackyTab);
        itemDorito = new ItemFood(1, 1, false).setUnlocalizedName("item_dorito").setCreativeTab(WackyGuesses.wackyTab);
        itemBananamobile = new ItemBananamobile().setUnlocalizedName("item_bananamobile").setCreativeTab(WackyGuesses.wackyTab);
        itemRecChoc = new ItemFood((int)2.5, 1, false).setUnlocalizedName("item_rectangular_chocolate").setCreativeTab(WackyGuesses.wackyTab);
        itemSphChoc = new ItemFood((int)2.5, 1, false).setUnlocalizedName("item_spherical_chocolate").setCreativeTab(WackyGuesses.wackyTab);
        itemEgg = new ItemFood(6, 4, false).setUnlocalizedName("item_egg").setCreativeTab(WackyGuesses.wackyTab);
        itemBullet = new Item().setUnlocalizedName("item_bullet");

        registerItems();
    }

    public static void registerItems()
    {
        GameRegistry.register(itemBanana, new ResourceLocation(WackyGuesses.MODID, "item_banana")).setCreativeTab(WackyGuesses.wackyTab);
        GameRegistry.register(itemBananamobile, new ResourceLocation(WackyGuesses.MODID, "item_bananamobile")).setCreativeTab(WackyGuesses.wackyTab);
        GameRegistry.register(itemDorito, new ResourceLocation(WackyGuesses.MODID, "item_dorito")).setCreativeTab(WackyGuesses.wackyTab);
        GameRegistry.register(itemRecChoc, new ResourceLocation(WackyGuesses.MODID, "item_rectangular_chocolate")).setCreativeTab(WackyGuesses.wackyTab);
        GameRegistry.register(itemSphChoc, new ResourceLocation(WackyGuesses.MODID, "item_spherical_chocolate")).setCreativeTab(WackyGuesses.wackyTab);
        GameRegistry.register(itemEgg, new ResourceLocation(WackyGuesses.MODID, "item_egg")).setCreativeTab(WackyGuesses.wackyTab);
        GameRegistry.register(itemBullet, new ResourceLocation(WackyGuesses.MODID, "item_bullet")).setCreativeTab(WackyGuesses.wackyTab);
    }

    public static void registerRenders()
    {
        registerRender(itemBanana);
        registerRender(itemBananamobile);
        registerRender(itemDorito);
        registerRender(itemRecChoc);
        registerRender(itemSphChoc);
        registerRender(itemEgg);
        registerRender(itemBullet);
    }

    public static void registerRender(Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation(WackyGuesses.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}

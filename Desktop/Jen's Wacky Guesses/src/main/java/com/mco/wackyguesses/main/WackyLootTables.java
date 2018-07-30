package com.mco.wackyguesses.main;

import com.mco.wackyguesses.WackyGuesses;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class WackyLootTables
{
    public static final ResourceLocation BANANA_BOSS = LootTableList.register(new ResourceLocation(WackyGuesses.MODID, "banana_boss"));
    public static final ResourceLocation BANANA = LootTableList.register(new ResourceLocation(WackyGuesses.MODID, "banana"));
    public static final ResourceLocation BANANAMOBILE = LootTableList.register(new ResourceLocation(WackyGuesses.MODID, "bananamobile"));
    public static final ResourceLocation DORITO = LootTableList.register(new ResourceLocation(WackyGuesses.MODID, "dorito"));
    public static final ResourceLocation CHOCO_BOX = LootTableList.register(new ResourceLocation(WackyGuesses.MODID, "box_of_chocolates"));
    public static final ResourceLocation RECTANGULAR_CHOCO = LootTableList.register(new ResourceLocation(WackyGuesses.MODID, "rectangular_chocolate"));
    public static final ResourceLocation SPHERICAL_CHOCO = LootTableList.register(new ResourceLocation(WackyGuesses.MODID, "spherical_chocolate"));
    public static final ResourceLocation EGG = LootTableList.register(new ResourceLocation(WackyGuesses.MODID, "egg"));
}

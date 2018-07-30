package com.mco.wackyguesses.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class WackyCreativeTab extends CreativeTabs
{
    public WackyCreativeTab(int index, String label)
    {
        super(index, label);
    }

    @Override
    public Item getTabIconItem()
    {
        return Items.DIAMOND;
    }
}

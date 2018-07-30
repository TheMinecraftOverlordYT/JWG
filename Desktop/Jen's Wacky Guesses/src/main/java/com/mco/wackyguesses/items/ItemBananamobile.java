package com.mco.wackyguesses.items;

import com.mco.wackyguesses.entities.bananamobile.EntityBananamobile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBananamobile extends Item
{
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if(!worldIn.isRemote)
        {
            System.out.println("world");
            EntityBananamobile bananamobile = new EntityBananamobile(worldIn);
            bananamobile.setLocationAndAngles(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.rotationYaw, playerIn.rotationPitch);
            worldIn.spawnEntityInWorld(bananamobile);
            if (!playerIn.capabilities.isCreativeMode)
            {
                --itemStackIn.stackSize;
            }
        }
        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }
}

package com.mco.wackyguesses.entities.bananamobile;

import com.mco.wackyguesses.main.WackyLootTables;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityBananamobile extends com.mco.wackyguesses.main.LibEntityVehicle
{
    public EntityBananamobile(World worldIn)
    {
        super(worldIn);
        this.setGroundVehicle();
        this.setSize(2, 1);
        this.setHealth(45F);
        this.setMaxSpeed(1F);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack)
    {
        if (hand == EnumHand.MAIN_HAND)
        {
            onInteract(player);
        }
        if (!isBeingRidden())
        {
            if (!player.worldObj.isRemote)
            {
                player.startRiding(this);
            }
            return true;
        }
        return false;
    }

    @Override
    public float getVehicleSpeed()
    {
        return 1F;
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return WackyLootTables.BANANAMOBILE;
    }

}

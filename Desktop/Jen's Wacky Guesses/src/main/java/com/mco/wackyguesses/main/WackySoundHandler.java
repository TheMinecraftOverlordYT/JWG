package com.mco.wackyguesses.main;

import com.mco.wackyguesses.WackyGuesses;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class WackySoundHandler
{
    public static SoundEvent LASER;
    public static SoundEvent BUZZ;
    public static SoundEvent MINIGUN;

    private static int size = 0;

    public static void init()
    {
        size = SoundEvent.REGISTRY.getKeys().size();

        LASER = register("entity.octobot.fire");
        BUZZ = register("entity.float_bee_gunner.ambient");
        MINIGUN = register("entity.float_bee_gunner.fire");
    }

    public static SoundEvent register(String name){
        ResourceLocation location = new ResourceLocation(WackyGuesses.MODID, name);
        SoundEvent e = new SoundEvent(location);

        SoundEvent.REGISTRY.register(size, location, e);
        size++;
        return e;
    }
}

package com.mco.wackyguesses.main;

import com.mco.wackyguesses.WackyGuesses;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class LibEntityRegistry
{

    private static int mobID = 0;
    /**
     * Register an entity with a name, class and egg colors
     *
     * @param name
     * @param entityClass
     * @param eggPrimary
     * @param eggSecondary
     */
    public static void registerEntity(String name, Class<? extends Entity> entityClass, int eggPrimary, int eggSecondary) {
        EntityRegistry.registerModEntity(entityClass, name, mobID++, WackyGuesses.instance, 64,
                3, true, eggPrimary, eggSecondary);

    }

    /**
     * Register an entity with a name and class
     *
     * @param name
     * @param entityClass
     */
    public static void registerEntityEggless(String name, Class<? extends Entity> entityClass) {
        EntityRegistry.registerModEntity(entityClass, name, mobID++, WackyGuesses.instance, 64,
                3, true);
    }

}

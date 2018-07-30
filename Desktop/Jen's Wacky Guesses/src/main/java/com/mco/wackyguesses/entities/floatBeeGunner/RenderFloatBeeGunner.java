package com.mco.wackyguesses.entities.floatBeeGunner;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFloatBeeGunner extends RenderLiving<EntityFloatBeeGunner>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/float_bee_gunner.png");
    public static final RenderFloatBeeGunner.Factory FACTORY =  new RenderFloatBeeGunner.Factory();

    public RenderFloatBeeGunner(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelFloatBeeGunner(), 1F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityFloatBeeGunner entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityFloatBeeGunner>
    {
        @Override
        public Render<? super EntityFloatBeeGunner> createRenderFor(RenderManager manager)
        {
            return new RenderFloatBeeGunner(manager);
        }
    }
}

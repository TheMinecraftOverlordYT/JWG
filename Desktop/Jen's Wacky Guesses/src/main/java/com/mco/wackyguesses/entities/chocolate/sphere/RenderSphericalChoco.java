package com.mco.wackyguesses.entities.chocolate.sphere;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderSphericalChoco extends RenderLiving<EntitySphericalChoco> 
{
    public static final String[] CHOCO_TEXTURES = new String[] {"wackyguesses:textures/entities/spherical_choco_dark.png", "wackyguesses:textures/entities/spherical_choco_milk.png", "wackyguesses:textures/entities/spherical_choco_nut.png", "wackyguesses:textures/entities/spherical_choco_white.png"};

    public static final RenderSphericalChoco.Factory FACTORY =  new RenderSphericalChoco.Factory();

    public RenderSphericalChoco(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSphericalChoco(), .7F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySphericalChoco entity)
    {
        return new ResourceLocation(CHOCO_TEXTURES[entity.getTextureInt()]);
    }

    public static class Factory implements IRenderFactory<EntitySphericalChoco>
    {
        @Override
        public Render<? super EntitySphericalChoco> createRenderFor(RenderManager manager)
        {
            return new RenderSphericalChoco(manager);
        }

    }
}

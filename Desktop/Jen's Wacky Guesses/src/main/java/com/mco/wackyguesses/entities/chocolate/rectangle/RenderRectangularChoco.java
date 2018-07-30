package com.mco.wackyguesses.entities.chocolate.rectangle;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderRectangularChoco extends RenderLiving<EntityRectangularChoco>
{
    public static final String[] CHOCO_TEXTURES = new String[] {"wackyguesses:textures/entities/rectangular_choco_dark.png", "wackyguesses:textures/entities/rectangular_choco_milk.png", "wackyguesses:textures/entities/rectangular_choco_nut.png", "wackyguesses:textures/entities/rectangular_choco_white.png"};

    public static final RenderRectangularChoco.Factory FACTORY =  new RenderRectangularChoco.Factory();

    public RenderRectangularChoco(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelRectangularChoco(), 1F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityRectangularChoco entity)
    {
        return new ResourceLocation(CHOCO_TEXTURES[entity.getTextureInt()]);
    }

    public static class Factory implements IRenderFactory<EntityRectangularChoco>
    {
        @Override
        public Render<? super EntityRectangularChoco> createRenderFor(RenderManager manager)
        {
            return new RenderRectangularChoco(manager);
        }

    }
}

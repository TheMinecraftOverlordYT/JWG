package com.mco.wackyguesses.entities.egg;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderEgg extends RenderLiving<EntityEgg> 
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/egg.png");
    public static final RenderEgg.Factory FACTORY =  new RenderEgg.Factory();

    public RenderEgg(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelEgg(), 1F);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityEgg entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(3F, 3F, 3F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityEgg entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityEgg>
    {
        @Override
        public Render<? super EntityEgg> createRenderFor(RenderManager manager)
        {
            return new RenderEgg(manager);
        }

    }
}

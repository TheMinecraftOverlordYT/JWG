package com.mco.wackyguesses.entities.patHead;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderPatHead extends RenderLiving<EntityPatHead> 
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/pat_head.png");
    public static final RenderPatHead.Factory FACTORY =  new RenderPatHead.Factory();

    public RenderPatHead(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPatHead(), 1F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPatHead entity)
    {
        return TEXTURES;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityPatHead entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.rotate(-90,0, 1, 0);
    }

    public static class Factory implements IRenderFactory<EntityPatHead>
    {
        @Override
        public Render<? super EntityPatHead> createRenderFor(RenderManager manager)
        {
            return new RenderPatHead(manager);
        }

    }
}

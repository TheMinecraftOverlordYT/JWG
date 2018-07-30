package com.mco.wackyguesses.entities.octobot;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderOctobot extends RenderLiving<EntityOctobot> 
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/octobot.png");
    public static final RenderOctobot.Factory FACTORY =  new RenderOctobot.Factory();

    public RenderOctobot(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelOctobot(), .7F);
        this.addLayer(new LayerOctobotLaserLeft<>(this));
        this.addLayer(new LayerOctobotLaserRight<>(this));
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityOctobot entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(2F, 2F, 2F);
    }


    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityOctobot entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityOctobot>
    {
        @Override
        public Render<? super EntityOctobot> createRenderFor(RenderManager manager)
        {
            return new RenderOctobot(manager);
        }

    }
}

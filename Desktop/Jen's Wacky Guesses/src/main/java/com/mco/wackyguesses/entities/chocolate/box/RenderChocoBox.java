package com.mco.wackyguesses.entities.chocolate.box;


import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderChocoBox extends RenderLiving<EntityChocoBox> 
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/choco_box.png");
    public static final RenderChocoBox.Factory FACTORY =  new RenderChocoBox.Factory();

    public RenderChocoBox(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelChocoBox(), 1F);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityChocoBox entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(3F, 3F, 3F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityChocoBox entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityChocoBox>
    {
        @Override
        public Render<? super EntityChocoBox> createRenderFor(RenderManager manager)
        {
            return new RenderChocoBox(manager);
        }

    }
}

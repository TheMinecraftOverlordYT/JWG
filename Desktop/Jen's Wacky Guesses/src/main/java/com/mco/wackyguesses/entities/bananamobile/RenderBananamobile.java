package com.mco.wackyguesses.entities.bananamobile;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBananamobile extends RenderLiving<EntityBananamobile>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/bananamobile.png");
    public static final RenderBananamobile.Factory FACTORY =  new RenderBananamobile.Factory();

    public RenderBananamobile(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBananamobile(), 1F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBananamobile entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityBananamobile>
    {
        @Override
        public Render<? super EntityBananamobile> createRenderFor(RenderManager manager)
        {
            return new RenderBananamobile(manager);
        }

    }
}

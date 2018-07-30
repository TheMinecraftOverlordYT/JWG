package com.mco.wackyguesses.entities.dorito;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDorito extends RenderLiving<EntityDorito> 
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/dorito.png");
    public static final RenderDorito.Factory FACTORY =  new RenderDorito.Factory();

    public RenderDorito(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelDorito(), 1F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDorito entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityDorito>
    {
        @Override
        public Render<? super EntityDorito> createRenderFor(RenderManager manager)
        {
            return new RenderDorito(manager);
        }

    }
}

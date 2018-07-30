package com.mco.wackyguesses.entities.ironPapa;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderIronPapa extends RenderLiving<EntityIronPapa> 
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/iron_papa.png");
    public static final RenderIronPapa.Factory FACTORY =  new RenderIronPapa.Factory();

    public RenderIronPapa(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelIronPapa(), 1F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityIronPapa entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityIronPapa>
    {
        @Override
        public Render<? super EntityIronPapa> createRenderFor(RenderManager manager)
        {
            return new RenderIronPapa(manager);
        }

    }
}

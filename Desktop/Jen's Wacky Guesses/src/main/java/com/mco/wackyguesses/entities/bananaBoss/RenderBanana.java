package com.mco.wackyguesses.entities.bananaBoss;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBanana extends RenderLiving<EntityBanana>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/banana_boss.png");
    public static final Factory FACTORY =  new Factory();

    public RenderBanana(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBanana(), 1F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBanana entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityBanana>
    {
        @Override
        public Render<? super EntityBanana> createRenderFor(RenderManager manager)
        {
            return new RenderBanana(manager);
        }

    }
}

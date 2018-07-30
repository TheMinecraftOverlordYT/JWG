package com.mco.wackyguesses.entities.floatBeeGunner.bee;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBee extends RenderLiving<EntityBee>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/bee.png");
    public static final RenderBee.Factory FACTORY =  new RenderBee.Factory();

    public RenderBee(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBee(), 1F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBee entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityBee>
    {
        @Override
        public Render<? super EntityBee> createRenderFor(RenderManager manager)
        {
            return new RenderBee(manager);
        }
    }
}

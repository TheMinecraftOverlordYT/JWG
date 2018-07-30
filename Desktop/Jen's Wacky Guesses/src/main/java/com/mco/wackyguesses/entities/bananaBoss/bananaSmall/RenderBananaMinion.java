package com.mco.wackyguesses.entities.bananaBoss.bananaSmall;

import com.mco.wackyguesses.entities.bananaBoss.ModelBanana;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBananaMinion extends RenderLiving<EntityBananaMinion>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("wackyguesses:textures/entities/banana_boss.png");
    public static final Factory FACTORY =  new Factory();

    public RenderBananaMinion(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBanana(), .25F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBananaMinion entity) {
        return TEXTURES;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityBananaMinion entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityBananaMinion entitylivingbaseIn, float partialTickTime)
    {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        GlStateManager.scale(.37F, .37F, .37F);
    }

    public static class Factory implements IRenderFactory<EntityBananaMinion>
    {

        @Override
        public Render<? super EntityBananaMinion> createRenderFor(RenderManager manager)
        {
            return new RenderBananaMinion(manager);
        }

    }
}

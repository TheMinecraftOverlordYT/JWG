package com.mco.wackyguesses.entities.angelicSpider;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAngelicSpider<T extends EntityAngelicSpider> extends RenderLiving<T>
{
    private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation("wackyguesses:textures/entities/angelic_spider.png");
    public static final RenderAngelicSpider.Factory FACTORY =  new RenderAngelicSpider.Factory();

    public RenderAngelicSpider(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelAngelicSpider(), 1.0F);
        this.addLayer(new LayerAngelicSpiderEyes<>(this));
    }

    protected float getDeathMaxRotation(T entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(T entity)
    {
        return SPIDER_TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityAngelicSpider>
    {
        @Override
        public Render<? super EntityAngelicSpider> createRenderFor(RenderManager manager)
        {
            return new RenderAngelicSpider(manager);
        }

    }
}

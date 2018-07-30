package com.mco.wackyguesses.entities.octobot;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerOctobotLaserRight<T extends EntityOctobot> implements LayerRenderer<T>
{
    private static final ResourceLocation octobotTextures = new ResourceLocation("wackyguesses", "textures/entities/tractor_beam.png");
    private final ModelBase modelmatter = new ModelOctobotLaser();
    private final RenderOctobot renderOctobot;

   public LayerOctobotLaserRight(RenderOctobot renderOctobotIn)
   {
     this.renderOctobot = renderOctobotIn;
   }
       
   public void func_177214_a(EntityOctobot p_177214_1_, float p_177214_2_, float p_177214_3_, float p_177214_4_, float p_177214_5_, float p_177214_6_, float p_177214_7_, float p_177214_8_)
   {
     if (p_177214_1_ != null && p_177214_1_.shouldRenderLasers())
     {
        GlStateManager.pushMatrix();
        boolean flag = p_177214_1_.isInvisible();
        GlStateManager.depthMask(!flag);
        //this.renderOctobot.getMainModel().HeadA.postRender(0.0625F);
        GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(.4F, 6.0F, .4F);
        GlStateManager.translate(.35F, .1F, -.7F);
        this.renderOctobot.bindTexture(octobotTextures);
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        float f = p_177214_1_.ticksExisted + p_177214_4_;
        GlStateManager.translate(0.0F, f * -0.02F, 0.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.enableBlend();
        float f1 = 0.5F;
        GlStateManager.color(f1, f1, f1, 1.0F);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        this.modelmatter.render(p_177214_1_, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(flag);
        GlStateManager.popMatrix();
     }
   }
       
   public boolean shouldCombineTextures()
   {
     return false;
   }
       
   @Override
   public void doRenderLayer(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
   {
        func_177214_a(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
   }
 }



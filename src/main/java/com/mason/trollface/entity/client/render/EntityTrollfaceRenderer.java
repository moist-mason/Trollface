package com.mason.trollface.entity.client.render;

import com.mason.trollface.Trollface;
import com.mason.trollface.entity.client.model.EntityTrollfaceModel;
import com.mason.trollface.entity.neutral.EntityTrollface;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EntityTrollfaceRenderer extends GeoEntityRenderer<EntityTrollface> {
    public EntityTrollfaceRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new EntityTrollfaceModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTrollface instance)
    {
        return new ResourceLocation(Trollface.MOD_ID, "textures/entity/trollface_entity.png");
    }

    public RenderType getRenderType(EntityTrollface animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation)
    {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

}

package com.mason.trollface.entity.client.render;

import com.mason.trollface.Trollface;
import com.mason.trollface.entity.client.model.TrollMobModel;
import com.mason.trollface.entity.client.model.TrollModelLayers;
import com.mason.trollface.entity.mob.neutral.EntityTrollface;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class EntityTrollfaceRenderer extends MobRenderer<EntityTrollface, TrollMobModel<EntityTrollface>> {
    public EntityTrollfaceRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TrollMobModel<>(pContext.bakeLayer(TrollModelLayers.TROLLFACE_ENTITY)), 0.5F);
    }

    @Override
    protected void scale(@Nonnull EntityTrollface pTrollface, PoseStack pStack, float pPartialTicks) {
        pStack.scale(1.0F, 1.0F, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTrollface pInstance) {
        return new ResourceLocation(Trollface.MOD_ID, "textures/entity/trollface_entity.png");
    }

}

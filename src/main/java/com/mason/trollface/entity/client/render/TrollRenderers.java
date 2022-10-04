package com.mason.trollface.entity.client.render;

import com.mason.trollface.Trollface;
import com.mason.trollface.entity.TrollEntityTypes;
import com.mason.trollface.entity.client.model.TrollMobModel;
import com.mason.trollface.entity.client.model.TrollModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Trollface.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TrollRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers pEvent) {
        pEvent.registerEntityRenderer(TrollEntityTypes.TROLLFACE_ENTITY.get(), EntityTrollfaceRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions pEvent) {
        pEvent.registerLayerDefinition(TrollModelLayers.TROLLFACE_ENTITY, TrollMobModel::createBodyLayer);
    }
}

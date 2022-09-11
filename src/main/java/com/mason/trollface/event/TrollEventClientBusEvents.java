package com.mason.trollface.event;

import com.mason.trollface.Trollface;
import com.mason.trollface.entity.TrollEntityTypes;
import com.mason.trollface.entity.client.render.EntityTrollfaceRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Trollface.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TrollEventClientBusEvents
{
    public static void clientSetup(final FMLClientSetupEvent event)
    {
        EntityRenderers.register(TrollEntityTypes.TROLLFACE_ENTITY.get(), EntityTrollfaceRenderer::new);
    }
}

package com.mason.trollface.world;

import com.mason.trollface.Trollface;
import com.mason.trollface.world.gen.TrollEntityGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Trollface.MOD_ID)
public class TrollWorldEvents {
    // Biome loading event used for naturally spawning all mobs.
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        TrollEntityGeneration.onEntitySpawn(event);
    }

}

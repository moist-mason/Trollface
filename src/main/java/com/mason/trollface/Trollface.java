package com.mason.trollface;

import com.mason.trollface.block.TrollBlocks;
import com.mason.trollface.entity.TrollEntityTypes;
import com.mason.trollface.entity.client.render.EntityTrollfaceRenderer;
import com.mason.trollface.entity.neutral.EntityTrollface;
import com.mason.trollface.item.TrollItems;
import com.mason.trollface.sound.TrollSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Trollface.MOD_ID)
public class Trollface {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "trollface";

    public Trollface() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TrollItems.register(eventBus);
        TrollBlocks.register(eventBus);

        TrollSounds.register(eventBus);

        TrollEntityTypes.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(TrollEntityTypes.TROLLFACE_ENTITY.get(), EntityTrollfaceRenderer::new);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(TrollEntityTypes.TROLLFACE_ENTITY.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    EntityTrollface::checkTrollfaceEntitySpawnRules);
        });
    }
}

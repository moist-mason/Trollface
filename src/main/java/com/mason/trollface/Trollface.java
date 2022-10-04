package com.mason.trollface;

import com.mason.trollface.block.TrollBlocks;
import com.mason.trollface.entity.TrollEntityTypes;
import com.mason.trollface.entity.mob.neutral.EntityTrollface;
import com.mason.trollface.item.TrollItems;
import com.mason.trollface.sound.TrollSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Trollface.MOD_ID)
public class Trollface {
    public static final String MOD_ID = "trollface";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Trollface() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);

        DeferredRegister<?>[] registers = {
                TrollBlocks.BLOCKS,
                TrollItems.ITEMS,
                TrollEntityTypes.ENTITY_TYPES,
                TrollSounds.SOUND_EVENTS
        };

        for (DeferredRegister<?> pRegister : registers) {
            pRegister.register(eventBus);
        }

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent pEvent) {
        pEvent.enqueueWork(() -> {
            SpawnPlacements.register(TrollEntityTypes.TROLLFACE_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTrollface::checkTrollfaceEntitySpawnRules);
        });
    }
}

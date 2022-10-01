package com.mason.trollface.world.gen.entity;

import com.mason.trollface.entity.TrollEntityTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Arrays;
import java.util.List;

public class TrollEntityGeneration {
    // The trollface mob only spawns in moderate tempered, non-humid forest biomes.
    public static void onEntitySpawn(final BiomeLoadingEvent pEvent) {
        addEntityToSpecificBiomes(pEvent, TrollEntityTypes.TROLLFACE_ENTITY.get(), 30, 2, 2, Biomes.DARK_FOREST, Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST);
    }

    @SafeVarargs
    private static void addEntityToSpecificBiomes(BiomeLoadingEvent pEvent, EntityType<?> pType, int pWeight, int pMinCount, int pMaxCount, ResourceKey<Biome>... pBiomes) {
        boolean isBiomeSelected = Arrays.stream(pBiomes).map(ResourceKey::location).map(Object::toString).anyMatch(s -> s.equals(pEvent.getName().toString()));

        if (isBiomeSelected) {
            addEntityToAllBiomes(pEvent, pType, pWeight, pMinCount, pMaxCount);
        }
    }

    private static void addEntityToAllBiomes(BiomeLoadingEvent pEvent, EntityType<?> pType, int pWeight, int pMinCount, int pMaxCount) {
        List<MobSpawnSettings.SpawnerData> base = pEvent.getSpawns().getSpawner(pType.getCategory());
        base.add(new MobSpawnSettings.SpawnerData(pType, pWeight, pMinCount, pMaxCount));
    }
}
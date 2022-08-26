package com.mason.trollface.entity;

import com.mason.trollface.Trollface;
import com.mason.trollface.entity.neutral.EntityTrollface;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Trollface.MOD_ID);

    public static final RegistryObject<EntityType<EntityTrollface>> TROLLFACE_ENTITY =
            ENTITY_TYPES.register("trollface_entity", () -> EntityType.Builder.of(EntityTrollface::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.2f)
                    .build(new ResourceLocation(Trollface.MOD_ID, "trollface_entity").toString()));

    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}

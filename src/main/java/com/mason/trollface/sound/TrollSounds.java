package com.mason.trollface.sound;

import com.mason.trollface.Trollface;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TrollSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Trollface.MOD_ID);

    // Trollface entity sounds
    public static final RegistryObject<SoundEvent> TROLLFACE_ENTITY_IDLE = regSoundEvent("trollface_entity_idle");
    public static final RegistryObject<SoundEvent> TROLLFACE_ENTITY_HURT = regSoundEvent("trollface_entity_hurt");
    public static final RegistryObject<SoundEvent> TROLLFACE_ENTITY_DEATH = regSoundEvent("trollface_entity_death");


    private static RegistryObject<SoundEvent> regSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(Trollface.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

package com.mason.trollface.event;

import com.mason.trollface.Trollface;
import com.mason.trollface.entity.EntityTypes;
import com.mason.trollface.entity.neutral.EntityTrollface;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Trollface.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents
{

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event)
    {
        event.put(EntityTypes.TROLLFACE_ENTITY.get(), EntityTrollface.setAttributes());
    }
}
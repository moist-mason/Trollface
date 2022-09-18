package com.mason.trollface.item;

import com.mason.trollface.Trollface;
import com.mason.trollface.entity.TrollEntityTypes;
import com.mason.trollface.item.custom.TotemofTrolling;
import com.mason.trollface.item.custom.TrollArmorMaterials;
import com.mason.trollface.item.custom.TrollPods;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TrollItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Trollface.MOD_ID);

    public static final RegistryObject<Item> TROLLFACE = ITEMS.register("trollface", () -> new Item(new Item.Properties().tab(TrollCreativeTab.TAB_TROLLFACE)));
    public static final RegistryObject<Item> TROLLFACE_ENTITY_SPAWN_EGG = ITEMS.register("trollface_entity_spawn_egg", () -> new ForgeSpawnEggItem(TrollEntityTypes.TROLLFACE_ENTITY, 0x000000, 0xffffff, new Item.Properties().tab(TrollCreativeTab.TAB_TROLLFACE)));
    public static final RegistryObject<Item> TOTEM_OF_TROLLING = ITEMS.register("totem_of_trolling", () -> new TotemofTrolling(new Item.Properties().tab(TrollCreativeTab.TAB_TROLLFACE).stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> TROLLPODS = ITEMS.register("trollpods", () -> new TrollPods(TrollArmorMaterials.TROLLPODS, EquipmentSlot.HEAD, new Item.Properties().tab(TrollCreativeTab.TAB_TROLLFACE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

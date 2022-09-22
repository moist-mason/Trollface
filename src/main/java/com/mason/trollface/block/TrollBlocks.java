package com.mason.trollface.block;

import com.mason.trollface.Trollface;
import com.mason.trollface.block.custom.BlockofTrollface;
import com.mason.trollface.item.TrollCreativeTab;
import com.mason.trollface.item.TrollItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TrollBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Trollface.MOD_ID);

    public static final RegistryObject<Block> TROLLFACE_BLOCK = registerBlock("trollface_block", () -> new BlockofTrollface(Block.Properties.of(Material.METAL).strength(70f).requiresCorrectToolForDrops()), TrollCreativeTab.TAB_TROLLFACE);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return TrollItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

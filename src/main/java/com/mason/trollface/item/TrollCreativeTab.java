package com.mason.trollface.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class TrollCreativeTab {
    public static final CreativeModeTab TAB_TROLLFACE = new CreativeModeTab("trollfacetab") {
        @Contract(" -> new")
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(TrollItems.TROLLFACE.get());
        }
    };
}

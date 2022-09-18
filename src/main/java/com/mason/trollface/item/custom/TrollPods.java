package com.mason.trollface.item.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class TrollPods extends ArmorItem {

    /* Aside from being a funny reference to the AirPods memes from a few years ago, The trollpods are heavily inspired by
     * the earplugs from the Ice and Fire mod. In that mod, earplugs are worn to protect the player from being lured by
     * sirens. Similarly, when the player wears trollpods, the giggle that plays when the player steps on a Block of Trollface is reduced,
     * and the block's negative effects are also reduced. Most of that is controlled in the BlockofTrollface class.
     */
    public TrollPods(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }
}

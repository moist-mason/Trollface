package com.mason.trollface.item.custom;

import com.mason.trollface.item.TrollItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@EventBusSubscriber
public class TotemofTrolling extends Item {
    public TotemofTrolling(Properties pProperties) {
        super(pProperties);
    }

    @SubscribeEvent
    public static boolean useTotem(LivingDeathEvent pEvent) {
        LivingEntity entity = pEvent.getEntityLiving();
        DamageSource source = pEvent.getSource();

        if (source.isBypassInvul()) {
            return false;
        } else {
            ItemStack stack = null;
            for (InteractionHand hand : InteractionHand.values()) {
                ItemStack stack1 = entity.getItemInHand(hand);
                if (stack1.is(TrollItems.TOTEM_OF_TROLLING.get())) {
                    stack = stack1.copy();
                    stack1.shrink(1);
                    break;
                }
            }

            if (stack != null) {
                entity.removeAllEffects();
            }

            return stack != null;
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("tooltip.trollface.totem_of_trolling"));
    }
}

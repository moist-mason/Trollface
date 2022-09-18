package com.mason.trollface.block.custom;

import com.mason.trollface.item.TrollItems;
import com.mason.trollface.item.custom.TrollPods;
import com.mason.trollface.sound.TrollSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockofTrollface extends Block {
    public BlockofTrollface(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(!pLevel.isClientSide()) {
            if(pEntity instanceof LivingEntity) {
                LivingEntity entity = ((LivingEntity) pEntity);

                if(entity instanceof Player) {
                    Player player = ((Player) entity);
                    ItemStack stack = player.getItemBySlot(EquipmentSlot.HEAD);
                    if(stack.is(TrollItems.TROLLPODS.get())) {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 5));
                        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, -5));
                    } else {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 10));
                        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, -10));
                        pLevel.playSound(player, pPos, TrollSounds.TROLLFACE_ENTITY_IDLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                } else {
                    ItemStack stack = entity.getItemBySlot(EquipmentSlot.HEAD);
                    if(stack.is(TrollItems.TROLLPODS.get())) {
                        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 5));
                        entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, -5));
                    } else {
                        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 10));
                        entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, -10));

                        /* Allows the player to wear trollpods and prevent the block of trollface giggles even if they
                         * aren't the ones stepping on the block.
                         */
                        Player player = ((Player) entity);
                        ItemStack stack1 = player.getItemBySlot(EquipmentSlot.HEAD);
                        if(!stack1.is(TrollItems.TROLLPODS.get())) {
                            pLevel.playSound(player, pPos, TrollSounds.TROLLFACE_ENTITY_IDLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }
}
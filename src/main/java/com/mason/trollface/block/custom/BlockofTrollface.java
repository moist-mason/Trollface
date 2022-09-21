package com.mason.trollface.block.custom;

import com.mason.trollface.item.TrollItems;
import com.mason.trollface.sound.TrollSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
                        blockEffects(player, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.JUMP, 10, 5, -5);
                    } else {
                        blockEffects(player, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.JUMP, 20, 10, -10);
                        pLevel.playSound(player, pPos, TrollSounds.TROLLFACE_ENTITY_IDLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                } else if(entity instanceof Monster) {
                    Monster monster = ((Monster) entity);
                    ItemStack stack = monster.getItemBySlot(EquipmentSlot.HEAD);
                    if(stack.is(TrollItems.TROLLPODS.get())) {
                        blockEffects(monster, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.JUMP, 10, 5, -5);
                    } else {
                        blockEffects(monster, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.JUMP, 10, 5, -5);

                        /* Allows the player to wear trollpods and mute the block of trollface giggles, even if they
                         * aren't the ones stepping on the block. */
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

    public void blockEffects(LivingEntity pEntity, MobEffect pEffect1, MobEffect pEffect2, int pDuration, int pAmplifier1, int pAmplifier2) {
        pEntity.addEffect(new MobEffectInstance(pEffect1, pDuration, pAmplifier1));
        pEntity.addEffect(new MobEffectInstance(pEffect2, pDuration, pAmplifier2));
    }
}
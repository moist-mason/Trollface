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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import javax.annotation.Nullable;

@EventBusSubscriber
public class BlockofTrollface extends Block {

    public BlockofTrollface(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide()) {
            if (pEntity instanceof Player player) {
                ItemStack stack = player.getItemBySlot(EquipmentSlot.HEAD);
                if (stack.is(TrollItems.TROLLPODS.get())) {
                    blockEffects(player, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.JUMP, 200, 1, -1);
                } else {
                    blockEffects(player, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.JUMP, 400, 3, -2);
                    pLevel.playSound(null, pPos, TrollSounds.TROLLFACE_ENTITY_IDLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            } else if (pEntity instanceof Monster monster) {
                ItemStack stack = monster.getItemBySlot(EquipmentSlot.HEAD);
                if (stack.is(TrollItems.TROLLPODS.get())) {
                    blockEffects(monster, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.JUMP, 200, 1, -1);
                } else {
                    blockEffects(monster, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.JUMP, 400, 3, -2);
                }
                /* Allows the player to wear trollpods and mute the block of trollface giggles, even if they
                 * aren't the ones stepping on the block. */
                Player player = pLevel.getNearestPlayer(monster, 16);
                assert player != null;
                if (!nearestPlayerWearingTrollPods(player)) {
                    pLevel.playSound(null, pPos, TrollSounds.TROLLFACE_ENTITY_IDLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            } else if (pEntity instanceof LivingEntity entity) {
                blockEffects(entity, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.JUMP, 400, 3, -2);

                Player player = pLevel.getNearestPlayer(pEntity, 16);
                assert player != null;
                if (!nearestPlayerWearingTrollPods(player)) {
                    pLevel.playSound(null, pPos, TrollSounds.TROLLFACE_ENTITY_IDLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }

    public void blockEffects(LivingEntity pEntity, MobEffect pEffect1, MobEffect pEffect2, int pDuration, int pAmplifier1, int pAmplifier2) {
        pEntity.addEffect(new MobEffectInstance(pEffect1, pDuration, pAmplifier1));
        pEntity.addEffect(new MobEffectInstance(pEffect2, pDuration, pAmplifier2));
    }

    public boolean nearestPlayerWearingTrollPods(Player pPlayer) {
        ItemStack stack = pPlayer.getItemBySlot(EquipmentSlot.HEAD);
        return stack.is(TrollItems.TROLLPODS.get());
    }

    // The trollface death sound plays when the block is broken by a player. This can be negated by wearing trollpods.
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pBlockEntity, pTool);
        ItemStack stack = pPlayer.getItemBySlot(EquipmentSlot.HEAD);
        if(!stack.is(TrollItems.TROLLPODS.get())) {
            pLevel.playSound(pPlayer, pPos, TrollSounds.TROLLFACE_ENTITY_DEATH.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
}
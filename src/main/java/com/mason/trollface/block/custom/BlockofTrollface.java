package com.mason.trollface.block.custom;

import com.mason.trollface.Trollface;
import com.mason.trollface.sound.TrollSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockofTrollface extends Block {
    public BlockofTrollface(Properties pProperties) {
        super(pProperties);
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide()) {
            if (pEntity instanceof LivingEntity) {
                LivingEntity entity = ((LivingEntity) pEntity);
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20));
                entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, -5));

                if (pLevel.getBlockState(pPos.relative(Direction.Axis.Z, 1).relative(Direction.Axis.X, 1).below()).is(this)) {
                    for (int i = 0; i > 1; i++) {
                        stepOnSoundEvent();
                    }
                }

                stepOnSoundEvent();
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    protected SoundEvent stepOnSoundEvent() {
        return TrollSounds.TROLLFACE_ENTITY_IDLE.get();
    }
}



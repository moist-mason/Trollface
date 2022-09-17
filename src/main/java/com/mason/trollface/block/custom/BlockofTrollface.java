package com.mason.trollface.block.custom;

import ca.weblite.objc.Client;
import com.mason.trollface.Trollface;
import com.mason.trollface.sound.TrollSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber
public class BlockofTrollface extends Block {
    public BlockofTrollface(Properties pProperties) {
        super(pProperties);
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        TickEvent.Phase phase = TickEvent.Phase.START;

        if (!pLevel.isClientSide()) {
            if (pEntity instanceof LivingEntity) {
                LivingEntity entity = ((LivingEntity) pEntity);
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20));
                entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, -5));

                if (entity instanceof Player) {
                    Player player = ((Player) entity);
                    PlayerTickEvent event = new PlayerTickEvent(phase, player);
                    checkPlayerStepEvent(pLevel, pPos, pState, player, event);
                } else {
                    checkStepEvent(pLevel, pPos, pState, pEntity);
                }
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @SubscribeEvent
    public void checkPlayerStepEvent(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer, PlayerTickEvent pEvent) {
        pPlayer = pEvent.player;
        pPos = pPlayer.blockPosition();

        for (int i = 0; i < 80; i++) {
            sendPosition(pPos.getX(), pPos.getY(), pPos.getZ(), true);
            sendPosition(pPos.getX() + 1, pPos.getY(), pPos.getZ() + 1, true);
        }
        sendPosition(pPos.getX(), pPos.getY(), pPos.getZ(), true);

        // stuff will go here

    }

    @SubscribeEvent
    public void checkStepEvent(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        // stuff will go here
    }

    private void sendPosition(int x, int y, int z, boolean onGround) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        player.connection.send(new ServerboundMovePlayerPacket.Pos(x, y, z, true));
    }


    protected SoundEvent stepOnSoundEvent() {
        return TrollSounds.TROLLFACE_ENTITY_IDLE.get();
    }
}



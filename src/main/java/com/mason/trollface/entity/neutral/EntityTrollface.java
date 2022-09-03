package com.mason.trollface.entity.neutral;

import com.mason.trollface.sound.TrollSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.*;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;
import java.util.UUID;


// Trollface has no animations, but it uses an animated geo model via the GeckoLib library.
// Any called classes or methods related to animation are required for things to work, but are either left blank or call an animation that doesn't do anything (animation.trollface.nil).
// I will attempt to change this in the future. -Mason
public class EntityTrollface extends Monster implements NeutralMob, IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    // Two variables related to how long it takes until the Trollface mob calms down after attack mode.
    private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.defineId(EntityTrollface.class, EntityDataSerializers.INT);
    private static final UniformInt ANGRY_TIMER = TimeUtil.rangeOfSeconds(30, 40);

    // Variable dictating the target of the Trollface mob's wrath.
    private UUID target;

    public EntityTrollface(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    // health, speed, and attack damage attributes.
    public static AttributeSupplier setAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.00)
                .add(Attributes.ATTACK_DAMAGE, 5.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(ANGER_TIME, 0);
    }

    // Trollface is a neutral mob. It generally likes to move around in random directions, but when it gets hit by another mob,
    // it will target that mob and attack it.
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1.0D, 16.0F));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 2.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    // Custom spawn rules for the Trollface mob.
    // The mob spawns in easy difficulty and above, it spawns on any block that an animal can spawn, and it spawns in any light level above 8.
    public static boolean checkTrollfaceEntitySpawnRules(EntityType<EntityTrollface> pType, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, Random pRandom) {
        return pLevel.getDifficulty() != Difficulty.PEACEFUL && pLevel.getBlockState(pPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && isBrightEnoughToSpawn(pLevel, pPos);
    }

    // Sets the light level that Trollface can spawn in, in this case at a level of 9 or above. A similar method can be found in the vanilla Animal class.
    protected static boolean isBrightEnoughToSpawn(BlockAndTintGetter pLevel, BlockPos pPos) {
        return pLevel.getRawBrightness(pPos, 0) > 8;
    }

    // Override of a method found in the vanilla Monster class. This override allows for the Trollface mob to spawn even in daylight.
    public float getWalkTargetValue(BlockPos pBlockPos, LevelReader pLevel) {
        return 0.0F;
    }

    // A max of 2 Trollface mobs can spawn in a chunk.
    public int getMaxSpawnClusterSize() {
        return 2;
    }

    // Methods related to the Trollface's neutral mob anger.
    @Override
    public int getRemainingPersistentAngerTime()
    {
        return this.entityData.get(ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int angerTime)
    {
        this.entityData.set(ANGER_TIME, angerTime);
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.target;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID angerTarget) {
        this.target = angerTarget;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(ANGRY_TIMER.sample(this.random));
    }

    // Useless animation method.
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.trollface.nil", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.trollface.nil", true));
        return PlayState.CONTINUE;
    }

    // Useless animation method.
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    // Useless animation method.
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    // Sound methods.
    protected SoundEvent getAmbientSound() {
        return TrollSounds.TROLLFACE_ENTITY_IDLE.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return TrollSounds.TROLLFACE_ENTITY_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return TrollSounds.TROLLFACE_ENTITY_DEATH.get();
    }

    // Override of a method in the Monster class.
    public boolean isPreventingPlayerRest(Player pPlayer) {
        return this.isAngryAt(pPlayer);
    }
}

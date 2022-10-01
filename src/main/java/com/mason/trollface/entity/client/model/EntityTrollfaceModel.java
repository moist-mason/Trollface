package com.mason.trollface.entity.client.model;

import com.mason.trollface.Trollface;
import com.mason.trollface.entity.mob.neutral.EntityTrollface;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EntityTrollfaceModel extends AnimatedGeoModel<EntityTrollface> {
    @Override
    public ResourceLocation getModelLocation(EntityTrollface pObject) {
        return new ResourceLocation(Trollface.MOD_ID, "geo/trollface_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTrollface pObject) {
        return new ResourceLocation(Trollface.MOD_ID, "textures/entity/trollface_entity.png");
    }

    // Trollface doesn't have animations, but this method must be implemented.
    @Override
    public ResourceLocation getAnimationFileLocation(EntityTrollface pAnimatable) {
        return new ResourceLocation(Trollface.MOD_ID, "animations/trollface.animation.json");
    }
}

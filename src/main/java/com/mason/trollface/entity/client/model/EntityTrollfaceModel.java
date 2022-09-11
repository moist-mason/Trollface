package com.mason.trollface.entity.client.model;

import com.mason.trollface.Trollface;
import com.mason.trollface.entity.neutral.EntityTrollface;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EntityTrollfaceModel extends AnimatedGeoModel<EntityTrollface> {
    @Override
    public ResourceLocation getModelLocation(EntityTrollface object) {
        return new ResourceLocation(Trollface.MOD_ID, "geo/trollface_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTrollface object) {
        return new ResourceLocation(Trollface.MOD_ID, "textures/entity/trollface_entity.png");
    }

    // Trollface doesn't have animations, but this method must be implemented.
    @Override
    public ResourceLocation getAnimationFileLocation(EntityTrollface animatable) {
        return new ResourceLocation(Trollface.MOD_ID, "animations/trollface.animation.json");
    }
}

package com.mason.trollface.entity.client.model;

import com.google.common.collect.Sets;
import com.mason.trollface.Trollface;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class TrollModelLayers {
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();

    public static final ModelLayerLocation TROLLFACE_ENTITY = register("trollface_entity");

    private static ModelLayerLocation register(String pPath) {
        return register(pPath, "main");
    }

    private static ModelLayerLocation register(String pPath, String pModel) {
        ModelLayerLocation location = new ModelLayerLocation(new ResourceLocation(Trollface.MOD_ID, pPath), pModel);
        if (!ALL_MODELS.add(location)) {
            throw new IllegalStateException("Duplicate registration for " + location);
        } else {
            return location;
        }
    }
}

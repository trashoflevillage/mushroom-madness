package io.github.trashoflevillage.mushroommadness.client.entity;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer MYCOLOGIST = registerMain("mycologist");

    private static EntityModelLayer registerMain(String id) {
        return new EntityModelLayer(Identifier.of(MushroomMadness.MOD_ID, id), "main");
    }
}

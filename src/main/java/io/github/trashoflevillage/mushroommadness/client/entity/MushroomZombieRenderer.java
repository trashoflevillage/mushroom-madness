package io.github.trashoflevillage.mushroommadness.client.entity;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.entity.custom.MushroomZombieEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

public class MushroomZombieRenderer extends MobEntityRenderer<MushroomZombieEntity, ZombieEntityModel<MushroomZombieEntity>> {
    public MushroomZombieRenderer(EntityRendererFactory.Context context, ZombieEntityModel<MushroomZombieEntity> entityModel, float f) {
        super(context, new ZombieEntityModel<>(context.getPart(ModModelLayers.MUSHROOM_ZOMBIE)), 0.5f);
    }

    @Override
    public Identifier getTexture(MushroomZombieEntity entity) {
        return Identifier.of(MushroomMadness.MOD_ID, "textures/entity/mushroom_zombie.png");
    }
}

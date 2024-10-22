package io.github.trashoflevillage.mushroommadness.client.entity;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.client.entity.models.MycologistEntityModel;
import io.github.trashoflevillage.mushroommadness.entity.custom.MycologistEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IllagerEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.util.Identifier;

public class MycologistRenderer extends MobEntityRenderer<MycologistEntity, MycologistEntityModel<MycologistEntity>> {

    public MycologistRenderer(EntityRendererFactory.Context context) {
        super(context, new MycologistEntityModel<>(context.getPart(ModModelLayers.MYCOLOGIST)), 0.5F);
    }

    @Override
    public Identifier getTexture(MycologistEntity entity) {
        return Identifier.of(MushroomMadness.MOD_ID, "textures/entity/mycologist.png");
    }
}

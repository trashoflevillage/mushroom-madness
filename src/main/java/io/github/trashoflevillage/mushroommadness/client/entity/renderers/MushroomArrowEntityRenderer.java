package io.github.trashoflevillage.mushroommadness.client.entity.renderers;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.entity.custom.MushroomArrowEntity;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;

public class MushroomArrowEntityRenderer extends ArrowEntityRenderer {
    public MushroomArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ArrowEntity arrowEntity) {
        String path;
        switch (((MushroomArrowEntity)arrowEntity).getMushroomType()) {
            case MushroomArrowEntity.MushroomArrowType.BROWN: path = "textures/entity/projectile/brown_mushroom_arrow.png"; break;
            case MushroomArrowEntity.MushroomArrowType.GLOWCAP: path = "textures/entity/projectile/glowcap_mushroom_arrow.png"; break;
            default: path = "textures/entity/projectile/red_mushroom_arrow.png"; break;
        }
        return Identifier.of(MushroomMadness.MOD_ID, path);
    }
}

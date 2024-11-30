package io.github.trashoflevillage.mushroommadness.client.entity.renderers;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.entity.custom.MushroomArrowEntity;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;

public class MushroomArrowEntityRenderer extends ProjectileEntityRenderer<MushroomArrowEntity> {
    public MushroomArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(MushroomArrowEntity entity) {
        String path;
        switch (entity.getMushroomType()) {
            case 1: path = "textures/entity/projectile/red_mushroom_arrow.png"; break;
            case 2: path = "textures/entity/projectile/brown_mushroom_arrow.png"; break;
            case 3: path = "textures/entity/projectile/glowcap_mushroom_arrow.png"; break;
            default: return Identifier.ofVanilla( "textures/entity/projectiles/arrow.png");
        }
        return Identifier.of(MushroomMadness.MOD_ID, path);
    }
}

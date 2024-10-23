package io.github.trashoflevillage.mushroommadness.client.entity.renderers;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.client.entity.ModModelLayers;
import io.github.trashoflevillage.mushroommadness.client.entity.models.MycologistEntityModel;
import io.github.trashoflevillage.mushroommadness.entity.custom.MycologistEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MycologistEntityRenderer<T extends MycologistEntity> extends MobEntityRenderer<MycologistEntity, MycologistEntityModel<MycologistEntity>> {
    public MycologistEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MycologistEntityModel<>(context.getPart(ModModelLayers.MYCOLOGIST)), 0.5F);
        this.addFeature(new HeadFeatureRenderer<MycologistEntity, MycologistEntityModel<MycologistEntity>>(this, context.getModelLoader(), context.getHeldItemRenderer()));
        this.addFeature(
                new HeldItemFeatureRenderer<MycologistEntity, MycologistEntityModel<MycologistEntity>>(this, context.getHeldItemRenderer()) {
                    public void render(
                            MatrixStack matrixStack,
                            VertexConsumerProvider vertexConsumerProvider,
                            int i,
                            MycologistEntity mycologistEntity,
                            float f,
                            float g,
                            float h,
                            float j,
                            float k,
                            float l
                    ) {
                        if (mycologistEntity.isSpellcasting() || mycologistEntity.isAttacking()) {
                            super.render(matrixStack, vertexConsumerProvider, i, mycologistEntity, f, g, h, j, k, l);
                        }
                    }
                }
        );
        this.addFeature(new MycologistMushroomFeatureRenderer<>(this, context.getBlockRenderManager()));
    }

    @Override
    public Identifier getTexture(MycologistEntity entity) {
        return Identifier.of(MushroomMadness.MOD_ID, "textures/entity/mycologist.png");
    }

    @Override
    protected void scale(MycologistEntity entity, MatrixStack matrices, float amount) {
        super.scale(entity, matrices, amount);
        matrices.scale(0.9375F, 0.9375F, 0.9375F);
    }
}

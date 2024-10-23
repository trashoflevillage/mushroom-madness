package io.github.trashoflevillage.mushroommadness.client.entity.renderers;


import io.github.trashoflevillage.mushroommadness.client.entity.models.MycologistEntityModel;
import io.github.trashoflevillage.mushroommadness.entity.custom.MycologistEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.util.math.RotationAxis;

public class MycologistMushroomFeatureRenderer<T extends MycologistEntity> extends FeatureRenderer<T, MycologistEntityModel<T>> {
    private final BlockRenderManager blockRenderManager;

    public MycologistMushroomFeatureRenderer(FeatureRendererContext<T, MycologistEntityModel<T>> context, BlockRenderManager blockRenderManager) {
        super(context);
        this.blockRenderManager = blockRenderManager;
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T mooshroomEntity, float f, float g, float h, float j, float k, float l) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        boolean bl = minecraftClient.hasOutline(mooshroomEntity) && mooshroomEntity.isInvisible();
        if (!mooshroomEntity.isInvisible() || bl) {
            BlockState blockState = Blocks.RED_MUSHROOM.getDefaultState();
            int m = LivingEntityRenderer.getOverlay(mooshroomEntity, 0.0F);
            BakedModel bakedModel = this.blockRenderManager.getModel(blockState);
            matrixStack.push();
            this.getContextModel().getHead().rotate(matrixStack);
            matrixStack.translate(0F, -0F, 0F);
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0F));
            matrixStack.scale(-1.0F, -1.0F, 1.0F);
            matrixStack.translate(-0.5F, 0.5F, -0.5F);
            this.renderMushroom(matrixStack, vertexConsumerProvider, i, bl, blockState, m, bakedModel);
            matrixStack.pop();
        }
    }

    private void renderMushroom(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            boolean renderAsModel,
            BlockState mushroomState,
            int overlay,
            BakedModel mushroomModel
    ) {
        if (renderAsModel) {
            this.blockRenderManager
                    .getModelRenderer()
                    .render(
                            matrices.peek(),
                            vertexConsumers.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)),
                            mushroomState,
                            mushroomModel,
                            0.0F,
                            0.0F,
                            0.0F,
                            light,
                            overlay
                    );
        } else {
            this.blockRenderManager.renderBlockAsEntity(mushroomState, matrices, vertexConsumers, light, overlay);
        }
    }
}

package io.github.trashoflevillage.mushroommadness.client;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.client.entity.ModModelLayers;
import io.github.trashoflevillage.mushroommadness.client.entity.renderers.MushroomArrowEntityRenderer;
import io.github.trashoflevillage.mushroommadness.client.entity.renderers.MycologistEntityRenderer;
import io.github.trashoflevillage.mushroommadness.client.entity.models.MycologistEntityModel;
import io.github.trashoflevillage.mushroommadness.entity.ModBoats;
import io.github.trashoflevillage.mushroommadness.entity.ModEntities;
import io.github.trashoflevillage.mushroommadness.particles.ModParticles;
import io.github.trashoflevillage.mushroommadness.particles.custom.GlowcapSporeAirParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;

public class MushroomMadnessClient implements ClientModInitializer {
	private static final Block[] blocksWithTransparency = new Block[] {
			ModBlocks.MYCELIUM_GROWTH, ModBlocks.SPORES, ModBlocks.DEAD_SPORES,
			ModBlocks.SPOREWOOD_DOOR, ModBlocks.SPOREWOOD_TRAPDOOR,
			ModBlocks.GLOWCAP, ModBlocks.WAXED_GLOWCAP, ModBlocks.WAXED_LIT_GLOWCAP,
			ModBlocks.WAXED_BROWN_MUSHROOM, ModBlocks.WAXED_RED_MUSHROOM,
			ModBlocks.GLOWCAP_ROOTS,
			ModBlocks.POTTED_GLOWCAP, ModBlocks.POTTED_WAXED_GLOWCAP, ModBlocks.POTTED_WAXED_LIT_GLOWCAP
	};

	@Override
	public void onInitializeClient() {
		for (Block i : blocksWithTransparency)
			BlockRenderLayerMap.INSTANCE.putBlock(i, RenderLayer.getCutout());

		TerraformBoatClientHelper.registerModelLayers(ModBoats.SPOREWOOD_BOAT_ID, false);

		registerEntityRenderersAndModelLayers();

		registerParticles();
		ModModelProvider.registerBowModels();
	}

	private void registerParticles() {
		ParticleFactoryRegistry.getInstance().register(
				ModParticles.GLOWCAP_SPORE,
				EndRodParticle.Factory::new
		);

		ParticleFactoryRegistry.getInstance().register(
				ModParticles.GLOWCAP_SPORE_AIR,
				GlowcapSporeAirParticle.GlowcapSporeAirFactory::new
		);
	}

	private void registerEntityRenderersAndModelLayers() {
		EntityRendererRegistry.register(ModEntities.MYCOLOGIST, MycologistEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MYCOLOGIST, MycologistEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(ModEntities.MUSHROOM_ARROW, MushroomArrowEntityRenderer::new);
	}
}
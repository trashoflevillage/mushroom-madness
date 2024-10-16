package io.github.trashoflevillage.mushroommadness.client;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.entities.ModBoats;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class MushroomMadnessClient implements ClientModInitializer {
	private static final Block[] blocksWithTransparency = new Block[] {
			ModBlocks.MYCELIUM_GROWTH, ModBlocks.SPORES,
			ModBlocks.SPOREWOOD_DOOR, ModBlocks.SPOREWOOD_TRAPDOOR
	};

	@Override
	public void onInitializeClient() {
		for (Block i : blocksWithTransparency)
			BlockRenderLayerMap.INSTANCE.putBlock(i, RenderLayer.getCutout());

		TerraformBoatClientHelper.registerModelLayers(ModBoats.SPOREWOOD_BOAT_ID, false);
	}
}
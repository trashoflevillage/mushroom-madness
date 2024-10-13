package io.github.trashoflevillage.mushroommadness.datagen;

import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerLog(ModBlocks.SPOREWOOD_LOG).log(ModBlocks.SPOREWOOD_LOG).wood(ModBlocks.SPOREWOOD_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_SPOREWOOD_LOG).log(ModBlocks.STRIPPED_SPOREWOOD_LOG).wood(ModBlocks.STRIPPED_SPOREWOOD_WOOD);

        BlockStateModelGenerator.BlockTexturePool sporewoodPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SPOREWOOD_PLANKS);

        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_SPOREWOOD_LOG, ModBlocks.SPOREWOOD_HANGING_SIGN, ModBlocks.SPOREWOOD_WALL_HANGING_SIGN);

        sporewoodPool.family(ModBlocks.SPOREWOOD_FAMILY);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}

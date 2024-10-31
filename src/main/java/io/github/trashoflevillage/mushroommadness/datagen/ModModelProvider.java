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

        sporewoodPool.stairs(ModBlocks.SPOREWOOD_STAIRS);
        sporewoodPool.slab(ModBlocks.SPOREWOOD_SLAB);
        sporewoodPool.button(ModBlocks.SPOREWOOD_BUTTON);
        sporewoodPool.pressurePlate(ModBlocks.SPOREWOOD_PRESSURE_PLATE);
        sporewoodPool.fence(ModBlocks.SPOREWOOD_FENCE);
        sporewoodPool.fenceGate(ModBlocks.SPOREWOOD_FENCE_GATE);

        blockStateModelGenerator.registerDoor(ModBlocks.SPOREWOOD_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.SPOREWOOD_TRAPDOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SPOREWOOD_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPOREWOOD_CHEST_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPOREWOOD_DISC, Models.GENERATED);

        itemModelGenerator.register(ModItems.MYCOLOGIST_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}

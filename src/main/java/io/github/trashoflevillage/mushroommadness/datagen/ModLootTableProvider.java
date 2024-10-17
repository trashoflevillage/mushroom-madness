package io.github.trashoflevillage.mushroommadness.datagen;

import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.SPOREWOOD_LOG);
        addDrop(ModBlocks.SPOREWOOD_WOOD);
        addDrop(ModBlocks.STRIPPED_SPOREWOOD_LOG);
        addDrop(ModBlocks.STRIPPED_SPOREWOOD_WOOD);
        addDrop(ModBlocks.SPOREWOOD_PLANKS);
        addDrop(ModBlocks.SPOREWOOD_SIGN);
        addDrop(ModBlocks.SPOREWOOD_WALL_SIGN, ModItems.SPOREWOOD_SIGN);
        addDrop(ModBlocks.SPOREWOOD_HANGING_SIGN);
        addDrop(ModBlocks.SPOREWOOD_WALL_HANGING_SIGN, ModItems.SPOREWOOD_HANGING_SIGN);
        addDrop(ModBlocks.SPOREWOOD_STAIRS);
        addDrop(ModBlocks.SPOREWOOD_SLAB, slabDrops(ModBlocks.SPOREWOOD_SLAB));
        addDrop(ModBlocks.SPOREWOOD_BUTTON);
        addDrop(ModBlocks.SPOREWOOD_PRESSURE_PLATE);
        addDrop(ModBlocks.SPOREWOOD_FENCE);
        addDrop(ModBlocks.SPOREWOOD_FENCE_GATE);
        addDrop(ModBlocks.SPOREWOOD_DOOR, doorDrops(ModBlocks.SPOREWOOD_DOOR));
        addDrop(ModBlocks.SPOREWOOD_TRAPDOOR);
        addDrop(ModBlocks.XP_COMPOSTER);
    }
}

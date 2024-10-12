package io.github.trashoflevillage.mushroommadness.datagen;

import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
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
    }
}

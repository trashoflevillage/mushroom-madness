package io.github.trashoflevillage.mushroommadness.datagen;

import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.util.ModConventionalTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModConventionalTags.Blocks.STRIPPED_LOGS)
                .add(Blocks.STRIPPED_ACACIA_LOG)
                .add(Blocks.STRIPPED_DARK_OAK_LOG)
                .add(Blocks.STRIPPED_OAK_LOG)
                .add(Blocks.STRIPPED_BIRCH_LOG)
                .add(Blocks.STRIPPED_SPRUCE_LOG)
                .add(Blocks.STRIPPED_CHERRY_LOG)
                .add(Blocks.STRIPPED_JUNGLE_LOG)
                .add(Blocks.STRIPPED_MANGROVE_LOG)
                .add(Blocks.STRIPPED_CRIMSON_STEM)
                .add(Blocks.STRIPPED_WARPED_STEM)
                .add(ModBlocks.STRIPPED_SPOREWOOD_LOG);

        getOrCreateTagBuilder(ModConventionalTags.Blocks.STRIPPED_WOODS)
                .add(Blocks.STRIPPED_ACACIA_WOOD)
                .add(Blocks.STRIPPED_DARK_OAK_WOOD)
                .add(Blocks.STRIPPED_OAK_WOOD)
                .add(Blocks.STRIPPED_BIRCH_WOOD)
                .add(Blocks.STRIPPED_SPRUCE_WOOD)
                .add(Blocks.STRIPPED_CHERRY_WOOD)
                .add(Blocks.STRIPPED_JUNGLE_WOOD)
                .add(Blocks.STRIPPED_MANGROVE_WOOD)
                .add(Blocks.STRIPPED_CRIMSON_HYPHAE)
                .add(Blocks.STRIPPED_WARPED_HYPHAE)
                .add(ModBlocks.STRIPPED_SPOREWOOD_WOOD);

        getOrCreateTagBuilder(ModConventionalTags.Blocks.WOODS)
                .add(Blocks.ACACIA_WOOD)
                .add(Blocks.DARK_OAK_WOOD)
                .add(Blocks.OAK_WOOD)
                .add(Blocks.BIRCH_WOOD)
                .add(Blocks.SPRUCE_WOOD)
                .add(Blocks.CHERRY_WOOD)
                .add(Blocks.JUNGLE_WOOD)
                .add(Blocks.MANGROVE_WOOD)
                .add(Blocks.CRIMSON_HYPHAE)
                .add(Blocks.WARPED_HYPHAE)
                .add(ModBlocks.SPOREWOOD_WOOD);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.SPOREWOOD_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.SPOREWOOD_FENCE_GATE);
    }
}

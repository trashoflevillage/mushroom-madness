package io.github.trashoflevillage.mushroommadness.datagen;

import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerBoatRecipe(exporter, ModItems.SPOREWOOD_BOAT, ModBlocks.SPOREWOOD_PLANKS);
        offerChestBoatRecipe(exporter, ModItems.SPOREWOOD_CHEST_BOAT, ModItems.SPOREWOOD_BOAT);

        createStairsRecipe(ModBlocks.SPOREWOOD_STAIRS, Ingredient.ofItems(ModBlocks.SPOREWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.SPOREWOOD_PLANKS), conditionsFromItem(ModBlocks.SPOREWOOD_PLANKS))
                .offerTo(exporter);
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPOREWOOD_SLAB, Ingredient.ofItems(ModBlocks.SPOREWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.SPOREWOOD_PLANKS), conditionsFromItem(ModBlocks.SPOREWOOD_PLANKS))
                .offerTo(exporter);
        createFenceRecipe(ModBlocks.SPOREWOOD_FENCE, Ingredient.ofItems(ModBlocks.SPOREWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.SPOREWOOD_PLANKS), conditionsFromItem(ModBlocks.SPOREWOOD_PLANKS))
                .offerTo(exporter);
        createFenceGateRecipe(ModBlocks.SPOREWOOD_FENCE_GATE, Ingredient.ofItems(ModBlocks.SPOREWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.SPOREWOOD_PLANKS), conditionsFromItem(ModBlocks.SPOREWOOD_PLANKS))
                .offerTo(exporter);
        createDoorRecipe(ModBlocks.SPOREWOOD_DOOR, Ingredient.ofItems(ModBlocks.SPOREWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.SPOREWOOD_PLANKS), conditionsFromItem(ModBlocks.SPOREWOOD_PLANKS))
                .offerTo(exporter);
        createTrapdoorRecipe(ModBlocks.SPOREWOOD_TRAPDOOR, Ingredient.ofItems(ModBlocks.SPOREWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.SPOREWOOD_PLANKS), conditionsFromItem(ModBlocks.SPOREWOOD_PLANKS))
                .offerTo(exporter);
        offerPressurePlateRecipe(exporter, ModBlocks.SPOREWOOD_PRESSURE_PLATE, ModBlocks.SPOREWOOD_PLANKS);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.SPOREWOOD_BUTTON, 1)
                .input(ModBlocks.SPOREWOOD_PLANKS)
                .criterion(hasItem(ModBlocks.SPOREWOOD_PLANKS), conditionsFromItem(ModBlocks.SPOREWOOD_PLANKS))
                .group("wooden_button")
                .offerTo(exporter);
    }
}

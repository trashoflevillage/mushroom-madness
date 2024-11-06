package io.github.trashoflevillage.mushroommadness.items;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.mixin.content.registry.BrewingRecipeRegistryBuilderMixin;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final Potion GLOWING_POTION =
            Registry.register(Registries.POTION, Identifier.of(MushroomMadness.MOD_ID, "glowing"),
                    new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 3600, 0)));
    public static final Potion EXTENDED_GLOWING_POTION =
            Registry.register(Registries.POTION, Identifier.of(MushroomMadness.MOD_ID, "glowing_extended"),
                    new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 3600 * 3, 0)));

    public static void registerPotionsRecipes() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    ModBlocks.GLOWCAP.asItem(),
                    Registries.POTION.getEntry(GLOWING_POTION)
            );

            builder.registerPotionRecipe(
                    Registries.POTION.getEntry(GLOWING_POTION),
                    Items.GLOWSTONE_DUST,
                    Registries.POTION.getEntry(EXTENDED_GLOWING_POTION)
            );
        });
    }
}

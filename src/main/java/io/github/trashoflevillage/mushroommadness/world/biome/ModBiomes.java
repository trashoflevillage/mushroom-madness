package io.github.trashoflevillage.mushroommadness.world.biome;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.world.ModPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicType;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> SPOREWOOD_FOREST = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(MushroomMadness.MOD_ID, "sporewood_forest"));

    public static void boostrap(Registerable<Biome> context) {
        context.register(SPOREWOOD_FOREST, sporewoodForest(context));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome sporewoodForest(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.MYCELIUM_GROWTH_PATCH_PLACED_KEY);

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SPOREWOOD_TREE_PLACED_KEY);
        DefaultBiomeFeatures.addMushroomMobs(spawnBuilder);
        DefaultBiomeFeatures.addMushroomFieldsFeatures(biomeBuilder);

        float temp = 1.0f;
        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.9f)
                .temperature(temp)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .grassColor(0x55c93f)
                        .skyColor(OverworldBiomeCreator.getSkyColor(temp))
                        .foliageColor(0x2bbb0f)
                        .build())
                .build();
    }
}

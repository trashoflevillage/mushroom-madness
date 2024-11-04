package io.github.trashoflevillage.mushroommadness.world;

import com.google.common.collect.ImmutableList;
import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.world.gen.decorators.SporeDecorator;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.CocoaBeansTreeDecorator;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPOREWOOD_TREE_KEY = registerKey("sporewood_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MYCELIUM_GROWTH_PATCH_KEY = registerKey("patch_mycelium_growth");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWCAP_PATCH_KEY = registerKey("patch_glowcap");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, SPOREWOOD_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.SPOREWOOD_LOG),
                new DarkOakTrunkPlacer(5, 4, 3),
                BlockStateProvider.of(Blocks.AIR),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),

                new TwoLayersFeatureSize(1, 0, 2)).decorators(ImmutableList.of(new SporeDecorator(1f))).build());

        register(
                context,
                MYCELIUM_GROWTH_PATCH_KEY,
                Feature.RANDOM_PATCH,
                createRandomPatchFeatureConfig(BlockStateProvider.of(ModBlocks.MYCELIUM_GROWTH),32)
        );

        register(
                context,
                GLOWCAP_PATCH_KEY,
                Feature.RANDOM_PATCH,
                createRandomPatchFeatureConfig(BlockStateProvider.of(ModBlocks.GLOWCAP),32)
        );
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MushroomMadness.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }
}

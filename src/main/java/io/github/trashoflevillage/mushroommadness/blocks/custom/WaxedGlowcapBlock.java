package io.github.trashoflevillage.mushroommadness.blocks.custom;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.particles.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class WaxedGlowcapBlock extends WaxedMushroomBlock  {
    public final boolean LIT;

    public WaxedGlowcapBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Settings settings, boolean lit) {
        super(featureKey, settings);
        LIT = lit;
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (LIT && random.nextFloat() < 0.25f) spawnParticle(world, pos, random);
    }

    private void spawnParticle(World world, BlockPos pos, Random rng) {
        world.addParticle(
                ModParticles.GLOWCAP_SPORE,
                pos.getX() + (rng.nextBetween(0, 20) * 0.05f),
                pos.getY() + (rng.nextBetween(0, 20) * 0.05f),
                pos.getZ() + (rng.nextBetween(0, 20) * 0.05f),
                0,
                rng.nextBetween(5, 10) * 0.01f,
                0
        );
    }
}

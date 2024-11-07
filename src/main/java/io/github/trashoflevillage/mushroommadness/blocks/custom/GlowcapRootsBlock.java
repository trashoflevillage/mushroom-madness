package io.github.trashoflevillage.mushroommadness.blocks.custom;

import io.github.trashoflevillage.mushroommadness.particles.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.HangingRootsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class GlowcapRootsBlock extends HangingRootsBlock {
    public GlowcapRootsBlock(Settings settings) {
        super(settings);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.25f) spawnParticle(world, pos, random);
    }

    private void spawnParticle(World world, BlockPos pos, Random rng) {
        world.addParticle(
                ModParticles.GLOWCAP_SPORE,
                pos.getX() + (rng.nextBetween(0, 20) * 0.05f),
                pos.getY() + (rng.nextBetween(0, 20) * 0.05f),
                pos.getZ() + (rng.nextBetween(0, 20) * 0.05f),
                0,
                rng.nextBetween(5, 10) * -0.01f,
                0
        );
    }
}

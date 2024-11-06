package io.github.trashoflevillage.mushroommadness.blocks.custom;

import io.github.trashoflevillage.mushroommadness.particles.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class GlowcapMushroomBlock extends MushroomBlock {
    public GlowcapMushroomBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        int particleRange = 10;
        mutable.set(
                i + MathHelper.nextInt(random, -particleRange, particleRange),
                j + MathHelper.nextInt(random, -particleRange, particleRange),
                k + MathHelper.nextInt(random, -particleRange, particleRange)
        );
        BlockState blockState = world.getBlockState(mutable);
        if (random.nextBetween(1, 5) == 1 && !blockState.isFullCube(world, mutable)) {
            world.addParticle(
                    ModParticles.GLOWCAP_SPORE_AIR,
                    (double)mutable.getX() + random.nextDouble(),
                    (double)mutable.getY() + random.nextDouble(),
                    (double)mutable.getZ() + random.nextDouble(),
                    0.0,
                    0.0,
                    0.0
            );
        }
    }
}

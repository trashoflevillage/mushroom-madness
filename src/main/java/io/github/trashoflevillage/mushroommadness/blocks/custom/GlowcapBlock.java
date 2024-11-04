package io.github.trashoflevillage.mushroommadness.blocks.custom;

import io.github.trashoflevillage.mushroommadness.particles.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class GlowcapBlock extends MushroomPlantBlock  {
    private long lastActivated = 0;
    public static final BooleanProperty LIT;

    public GlowcapBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Settings settings) {
        super(featureKey, settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(LIT, false));
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        activate(state, pos, world);
    }

    private void activate(BlockState state, BlockPos pos, World world) {
        lastActivated = world.getTime();

        if (!state.get(LIT)) {
            world.setBlockState(pos, state.with(LIT, true));
            Random rng = world.getRandom();
            for (int i = 0; i < 4; i++) {
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
    }

    private void deactivate(BlockState state, BlockPos pos, ServerWorld world) {
        world.setBlockState(pos, state.with(LIT, false));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(LIT, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getTime() - lastActivated > 600) deactivate(state, pos, world);
    }

    static {
        LIT = RedstoneTorchBlock.LIT;
    }
}

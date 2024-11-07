package io.github.trashoflevillage.mushroommadness.blocks.custom;

import com.mojang.serialization.MapCodec;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.util.ManualConventionalTags;
import io.github.trashoflevillage.mushroommadness.util.ModTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class SporesBlock extends MultifaceGrowthBlock implements Fertilizable, Waterloggable {
    private static final BooleanProperty WATERLOGGED;
    private final SporesGrower grower = new SporesGrower(this);
    private final boolean canSpread;

    public SporesBlock(Settings settings) {
        super(settings);

        setDefaultState((BlockState)this.getDefaultState().with(WATERLOGGED, false));
        canSpread = true;
    }

    public SporesBlock(Settings settings, boolean canSpread) {
        super(settings);

        setDefaultState((BlockState)this.getDefaultState().with(WATERLOGGED, false));
        this.canSpread = canSpread;
    }

    @Override
    protected MapCodec<? extends MultifaceGrowthBlock> getCodec() {
        return null;
    }

    @Override
    public LichenGrower getGrower() {
        return this.grower;
    }

    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return Direction.stream().anyMatch((direction) -> {
            return this.grower.canGrow(state, world, pos, direction.getOpposite());
        });
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return canSpread;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.grower.grow(state, world, pos, random);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSpread) return;

        if (random.nextFloat() < 0.6 && this.canGrow(world, random, pos, state)) {
            this.grow(world, random, pos, state);
            if (random.nextFloat() < 0.2)
                this.die(state, world, pos);
        }

        for (BlockPos i : this.getAttachedBlockPositions(state, pos)) {
            if (random.nextFloat() < 0.3) attemptConvertBlock(world, i);
        }
    }

    private void die(BlockState state, ServerWorld world, BlockPos pos) {
        Collection<Property<?>> properties = state.getProperties();
        BlockState newState = ModBlocks.DEAD_SPORES.getDefaultState();

        for (Property i : properties)
            newState = newState.with(i, state.get(i));

        world.setBlockState(pos, newState);
    }

    private void attemptConvertBlock(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (!state.isIn(ModTags.Blocks.SPOREWOOD_LOGS) && state.isIn(BlockTags.LOGS)) {
            BlockState newState;
            if (state.isIn(ManualConventionalTags.Blocks.STRIPPED_WOODS)) newState = ModBlocks.STRIPPED_SPOREWOOD_WOOD.getDefaultState();
            else if (state.isIn(ManualConventionalTags.Blocks.WOODS)) newState = ModBlocks.SPOREWOOD_WOOD.getDefaultState();
            else if (state.isIn(ManualConventionalTags.Blocks.STRIPPED_LOGS)) newState = ModBlocks.STRIPPED_SPOREWOOD_LOG.getDefaultState();
            else newState = ModBlocks.SPOREWOOD_LOG.getDefaultState();

            world.setBlockState(pos, newState.with(PillarBlock.AXIS, world.getBlockState(pos).get(PillarBlock.AXIS)));
        }

        if (state.isIn(BlockTags.LEAVES)) {
            world.breakBlock(pos, false);
        }
    }

    protected FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    private ArrayList<BlockPos> getAttachedBlockPositions(BlockState state, BlockPos pos) {
        ArrayList<BlockPos> output = new ArrayList<>();
        for (Direction i : DIRECTIONS) {
            BooleanProperty p = MultifaceGrowthBlock.getProperty(i);
            if (state.get(p).booleanValue()) { // Block is placed on this directional face.
                BlockPos restingPos = pos.add(i.getVector()); // The position that the blockstate is sitting on.
                output.add(restingPos);
            }
        }
        return output;
    }

    private ArrayList<BlockState> getAttachedBlockStates(WorldAccess world, BlockState state, BlockPos pos) {
        ArrayList<BlockState> output = new ArrayList<>();
        for (Direction i : DIRECTIONS) {
            BooleanProperty p = MultifaceGrowthBlock.getProperty(i);
            if (state.get(p).booleanValue()) { // Block is placed on this directional face.
                BlockPos restingPos = pos.add(i.getVector()); // The position that the blockstate is sitting on.
                output.add(world.getBlockState(restingPos));
            }
        }
        return output;
    }

    class SporesGrower extends LichenGrower {
        private GrowChecker growChecker;

        public SporesGrower(MultifaceGrowthBlock lichen) {
            super(lichen);
            this.growChecker = new LichenGrowChecker(lichen);
        }

        public SporesGrower(GrowChecker growChecker) {
            super(growChecker);
            this.growChecker = growChecker;
        }

        @Override
        public Optional<GrowPos> place(WorldAccess world, GrowPos pos, boolean markForPostProcessing) {
            boolean o = true;
            BlockState state = world.getBlockState(pos.pos());
            BlockState blockState = growChecker.getStateWithDirection(state, world, pos.pos(), pos.face());
            if (blockState != null) {
                if (markForPostProcessing) {
                    world.getChunk(pos.pos()).markBlockForPostProcessing(pos.pos());
                }

                ArrayList<BlockPos> attached = getAttachedBlockPositions(blockState, pos.pos());
                for (BlockPos i : attached) {
                    if (!world.getBlockState(i).isIn(ModTags.Blocks.SPORES_SPREADABLE)) {
                        o = false;
                        break;
                    }
                }

                if (o != false)
                    o = world.setBlockState(pos.pos(), blockState, 2);
            } else {
                o = false;
            }

            return o ? Optional.of(pos) : Optional.empty();
        }
    }


    static {
        WATERLOGGED = Properties.WATERLOGGED;
    }
}

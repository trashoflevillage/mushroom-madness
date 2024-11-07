package io.github.trashoflevillage.mushroommadness.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class MushroomShelfBlock extends Block {
    private static int MAX_SHELF_SHROOMS = 3;

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty COUNT = IntProperty.of("count", 1, MAX_SHELF_SHROOMS);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    protected static final VoxelShape ONE_SHELF_SHAPE_SOUTH =
            Block.createCuboidShape(2, 13, 4, 14, 14, 16);

    protected static final VoxelShape TWO_SHELF_SHAPE_SOUTH =
            VoxelShapes.combineAndSimplify(Block.createCuboidShape(6, 12, 8, 15, 13, 16), Block.createCuboidShape(1, 8, 8, 10, 9, 16), BooleanBiFunction.OR);

    protected static final VoxelShape THREE_SHELF_SHAPE_SOUTH = Stream.of(
            Block.createCuboidShape(8, 4, 10, 14, 5, 16),
            Block.createCuboidShape(1, 7, 10, 7, 8, 16),
            Block.createCuboidShape(8, 10, 10, 14, 11, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    protected static final VoxelShape ONE_SHELF_SHAPE_WEST =
            Block.createCuboidShape(0, 13, 2, 12, 14, 14);

    protected static final VoxelShape TWO_SHELF_SHAPE_WEST =
            VoxelShapes.combineAndSimplify(Block.createCuboidShape(0, 12, 6, 8, 13, 15), Block.createCuboidShape(0, 8, 1, 8, 9, 10), BooleanBiFunction.OR);

    protected static final VoxelShape THREE_SHELF_SHAPE_WEST = Stream.of(
            Block.createCuboidShape(0, 4, 8, 6, 5, 14),
            Block.createCuboidShape(0, 7, 1, 6, 8, 7),
            Block.createCuboidShape(0, 10, 8, 6, 11, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    protected static final VoxelShape ONE_SHELF_SHAPE_NORTH =
            Block.createCuboidShape(2, 13, 0, 14, 14, 12);

    protected static final VoxelShape TWO_SHELF_SHAPE_NORTH =
            VoxelShapes.combineAndSimplify(Block.createCuboidShape(1, 12, 0, 10, 13, 8), Block.createCuboidShape(6, 8, 0, 15, 9, 8), BooleanBiFunction.OR);

    protected static final VoxelShape THREE_SHELF_SHAPE_NORTH = Stream.of(
            Block.createCuboidShape(2, 4, 0, 8, 5, 6),
            Block.createCuboidShape(9, 7, 0, 15, 8, 6),
            Block.createCuboidShape(2, 10, 0, 8, 11, 6)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    protected static final VoxelShape ONE_SHELF_SHAPE_EAST =
            Block.createCuboidShape(4, 13, 2, 16, 14, 14);

    protected static final VoxelShape TWO_SHELF_SHAPE_EAST =
            VoxelShapes.combineAndSimplify(Block.createCuboidShape(8, 12, 1, 16, 13, 10), Block.createCuboidShape(8, 8, 6, 16, 9, 15), BooleanBiFunction.OR);

    protected static final VoxelShape THREE_SHELF_SHAPE_EAST = Stream.of(
                    Block.createCuboidShape(10, 4, 2, 16, 5, 8),
                    Block.createCuboidShape(10, 7, 9, 16, 8, 15),
                    Block.createCuboidShape(10, 10, 2, 16, 11, 8)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public MushroomShelfBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(COUNT, Integer.valueOf(1)).with(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(COUNT);
        builder.add(WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.with(COUNT, Integer.valueOf(Math.min(4, (Integer)blockState.get(COUNT) + 1)));
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
            boolean bl = fluidState.getFluid() == Fluids.WATER;
            BlockState finalState = super.getPlacementState(ctx).with(WATERLOGGED, Boolean.valueOf(bl));
            Direction dir = ctx.getPlacementDirections()[0];
            if (dir.getAxis() == Direction.Axis.Y) dir = ctx.getHorizontalPlayerFacing();
            finalState = finalState.with(FACING, dir);
            return finalState;
        }
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().isOf(this.asItem()) && state.get(COUNT) < MAX_SHELF_SHROOMS || super.canReplace(state, context);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case Direction.NORTH:
                switch (state.get(COUNT)) {
                    case 1: return ONE_SHELF_SHAPE_NORTH;
                    case 2: return TWO_SHELF_SHAPE_NORTH;
                    case 3: return THREE_SHELF_SHAPE_NORTH;
                }
            case Direction.EAST:
                switch (state.get(COUNT)) {
                    case 1: return ONE_SHELF_SHAPE_EAST;
                    case 2: return TWO_SHELF_SHAPE_EAST;
                    case 3: return THREE_SHELF_SHAPE_EAST;
                }
            case Direction.WEST:
                switch (state.get(COUNT)) {
                    case 1: return ONE_SHELF_SHAPE_WEST;
                    case 2: return TWO_SHELF_SHAPE_WEST;
                    case 3: return THREE_SHELF_SHAPE_WEST;
                }
            case Direction.SOUTH:
                switch (state.get(COUNT)) {
                    case 1: return ONE_SHELF_SHAPE_SOUTH;
                    case 2: return TWO_SHELF_SHAPE_SOUTH;
                    case 3: return THREE_SHELF_SHAPE_SOUTH;
                }
        }
        // This return statement should never be reached. If it's reached, something went wrong.
        return ONE_SHELF_SHAPE_NORTH;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(COUNT) == 1) return super.getCollisionShape(state, world, pos, context);
        return Block.createCuboidShape(0, 0, 0, 0, 0, 0);
    }
}

package io.github.trashoflevillage.mushroommadness.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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

import java.util.Optional;

public class SporesBlock extends MultifaceGrowthBlock implements Fertilizable, Waterloggable {
    public static final IntProperty TEXTURE = IntProperty.of("texture", 0, 2);
    private static final BooleanProperty WATERLOGGED;
    private final SporesGrower grower = new SporesGrower(this);

    public SporesBlock(Settings settings) {
        super(settings);

        setDefaultState(getDefaultState().with(TEXTURE, 0));
        setDefaultState((BlockState)this.getDefaultState().with(WATERLOGGED, false));
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
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.grower.grow(state, world, pos, random);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(TEXTURE);
        builder.add(WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        int textureState = getRandomTextureState(ctx.getWorld());
        BlockState blockState = super.getPlacementState(ctx);
        if (blockState == null) return null;
        return blockState.with(TEXTURE, textureState);
    }

    private static int getRandomTextureState(World world) {
        return world.random.nextBetween(0, 2);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.3 && this.canGrow(world, random, pos, state))
            this.grow(world, random, pos, state);
    }

    protected FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
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
            boolean o;
            BlockState state = world.getBlockState(pos.pos());
            BlockState blockState = growChecker.getStateWithDirection(state, world, pos.pos(), pos.face());
            if (blockState != null) {
                if (markForPostProcessing) {
                    world.getChunk(pos.pos()).markBlockForPostProcessing(pos.pos());
                }

                o = world.setBlockState(pos.pos(), blockState.with(TEXTURE, world.getRandom().nextBetween(0, 2)), 2);
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

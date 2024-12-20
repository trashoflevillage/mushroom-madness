package io.github.trashoflevillage.mushroommadness.blocks.custom;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.particles.ModParticles;
import io.github.trashoflevillage.mushroommadness.sounds.ModSounds;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class GlowcapBlock extends MushroomPlantBlock  {
    public static final Identifier COLLIDE_WITH_GLOWCAP_PACKET_ID =
            Identifier.of(MushroomMadness.MOD_ID, "glowcap_collision");
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
            world.playSoundAtBlockCenter(pos, ModSounds.TOGGLE_GLOWCAP, SoundCategory.BLOCKS, 1f, 1f, true);
            world.setBlockState(pos, state.with(LIT, true));
            Random rng = world.getRandom();
            for (int i = 0; i < 4; i++) {
                spawnParticle(world, pos, rng);
            }
        }
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT) && random.nextFloat() < 0.25f) spawnParticle(world, pos, random);
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

    private void deactivate(BlockState state, BlockPos pos, ServerWorld world) {
        world.setBlockState(pos, state.with(LIT, false));
        world.playSoundAtBlockCenter(pos, ModSounds.TOGGLE_GLOWCAP, SoundCategory.BLOCKS, 0.5f, 1f, true);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(LIT, false);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT) && world.getTime() - lastActivated > 600) deactivate(state, pos, world);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isOf(Items.HONEYCOMB)) {
            waxBlock(stack, state, world, pos, player, hand);
            return ItemActionResult.SUCCESS;
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    private void waxBlock(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        stack.decrementUnlessCreative(1, player);
        player.setStackInHand(hand, stack);

        if (!state.get(LIT)) world.setBlockState(pos, ModBlocks.WAXED_GLOWCAP.getDefaultState());
        else world.setBlockState(pos, ModBlocks.WAXED_LIT_GLOWCAP.getDefaultState());
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, state));
        world.syncWorldEvent(player, WorldEvents.BLOCK_WAXED, pos, 0);
    }

//    @Override
//    public boolean trySpawningBigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random random) {
//        if (super.trySpawningBigMushroom(world, pos, state, random)) {
//            Direction[] horizontalDirections = new Direction[] {
//                    Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST
//            };
//            BlockPos currentPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
//            while (world.getBlockState(currentPos).isOf(Blocks.MUSHROOM_STEM)) {
//                for (Direction d : horizontalDirections) {
//                    if (random.nextFloat() <= 1f/3) {
//                        world.setBlockState(
//                                currentPos.offset(d, 1),
//                                ModBlocks.GLOWCAP_SHELF_MUSHROOM.getDefaultState()
//                                        .with(MushroomShelfBlock.FACING, d)
//                                        .with(MushroomShelfBlock.COUNT, random.nextBetween(1, 3))
//                        );
//                    }
//                }
//                currentPos = currentPos.add(0, 1, 0);
//            }
//            return true;
//        }
//        return false;
//    }

    static {
        LIT = RedstoneTorchBlock.LIT;
    }
}

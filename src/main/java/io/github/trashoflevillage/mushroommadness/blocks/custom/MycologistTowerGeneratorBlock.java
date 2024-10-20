package io.github.trashoflevillage.mushroommadness.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MycologistTowerGeneratorBlock extends Block {
    public MycologistTowerGeneratorBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.removeBlock(pos, false);
        generateStructure(world, pos);
    }

    public static void generateStructure(World world, BlockPos pos) {
        for (int y = pos.getY(); !world.isOutOfHeightLimit(y); y++) {
            BlockPos newPos = new BlockPos(pos.getX(), y, pos.getZ());
            world.setBlockState(newPos, Blocks.COBBLESTONE.getDefaultState());
        }
    }
}

package io.github.trashoflevillage.mushroommadness.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class CustomShortPlantBlock extends ShortPlantBlock {
    public CustomShortPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return false;
    }
}

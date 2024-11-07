package io.github.trashoflevillage.mushroommadness.world.gen.decorators;

import io.github.trashoflevillage.mushroommadness.blocks.custom.MushroomShelfBlock;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public abstract class AbstractShelfMushroomDecorator extends TreeDecorator implements IMushroomShelfDecorator {
    public final float probability; //0.0 -> 1.0        0.00 = 0% , 1.00 = 100%
    public AbstractShelfMushroomDecorator(float f){
        this.probability = f;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        Block shelfType = getShelfType();
        generator.getLogPositions().forEach(pos -> {
            BlockPos blockPos;
            if (random.nextFloat() < this.probability && generator.isAir(blockPos = pos.west())) {
                generator.replace(blockPos, shelfType.getDefaultState()
                        .with(MushroomShelfBlock.FACING, Direction.EAST)
                        .with(MushroomShelfBlock.COUNT, random.nextBetween(1, 3))
                );
            }
            if (random.nextFloat() < this.probability && generator.isAir(blockPos = pos.east())) {
                generator.replace(blockPos, shelfType.getDefaultState()
                        .with(MushroomShelfBlock.FACING, Direction.WEST)
                        .with(MushroomShelfBlock.COUNT, random.nextBetween(1, 3))
                );
            }
            if (random.nextFloat() < this.probability && generator.isAir(blockPos = pos.north())) {
                generator.replace(blockPos, shelfType.getDefaultState()
                        .with(MushroomShelfBlock.FACING, Direction.SOUTH)
                        .with(MushroomShelfBlock.COUNT, random.nextBetween(1, 3))
                );
            }
            if (random.nextFloat() < this.probability && generator.isAir(blockPos = pos.south())) {
                generator.replace(blockPos, shelfType.getDefaultState()
                        .with(MushroomShelfBlock.FACING, Direction.NORTH)
                        .with(MushroomShelfBlock.COUNT, random.nextBetween(1, 3))
                );
            }
        });
    }
}
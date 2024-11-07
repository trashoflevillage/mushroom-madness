package io.github.trashoflevillage.mushroommadness.world.gen.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.blocks.custom.SporesBlock;
import io.github.trashoflevillage.mushroommadness.world.gen.ModTreeDecorator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class SporeDecorator extends TreeDecorator {

    public static TreeDecoratorType<SporeDecorator> TYPE;
    public final float probability; //0.0 -> 1.0        0.00 = 0% , 1.00 = 100%
    public SporeDecorator(float f){this.probability = f;}
    public TreeDecoratorType<?> getType() {return TYPE;}

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        if (!(random.nextFloat() >= this.probability)) {
            generator.getLogPositions().forEach(pos -> {
                BlockPos blockPos;
                if (random.nextInt(3) > 0 && generator.isAir(blockPos = pos.west())) {
                    generator.replace(blockPos, ModBlocks.DEAD_SPORES.getDefaultState().with(SporesBlock.getProperty(Direction.EAST), true));
                }
                if (random.nextInt(3) > 0 && generator.isAir(blockPos = pos.east())) {
                    generator.replace(blockPos, ModBlocks.DEAD_SPORES.getDefaultState().with(SporesBlock.getProperty(Direction.WEST), true));
                }
                if (random.nextInt(3) > 0 && generator.isAir(blockPos = pos.north())) {
                    generator.replace(blockPos, ModBlocks.DEAD_SPORES.getDefaultState().with(SporesBlock.getProperty(Direction.SOUTH), true));
                }
                if (random.nextInt(3) > 0 && generator.isAir(blockPos = pos.south())) {
                    generator.replace(blockPos, ModBlocks.DEAD_SPORES.getDefaultState().with(SporesBlock.getProperty(Direction.NORTH), true));
                }
            });
        }
    }
}
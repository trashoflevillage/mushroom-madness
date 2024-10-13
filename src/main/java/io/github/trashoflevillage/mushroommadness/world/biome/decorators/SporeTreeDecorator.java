package io.github.trashoflevillage.mushroommadness.world.biome.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.blocks.custom.SporesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Iterator;
import java.util.List;

public class SporeTreeDecorator extends TreeDecorator {
    public static final MapCodec<SporeTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(SporeTreeDecorator::new, (decorator) -> {
        return decorator.probability;
    });
    private final float probability;

    public SporeTreeDecorator(float probability) {
        this.probability = probability;
    }

    protected TreeDecoratorType<?> getType() {
        return null;
    }

    public void generate(TreeDecorator.Generator generator) {
        Random random = generator.getRandom();
        if (!(random.nextFloat() >= this.probability)) {
            List<BlockPos> list = generator.getLogPositions();
            int i = ((BlockPos)list.get(0)).getY();
            list.stream().filter((pos) -> {
                return pos.getY() - i <= 2;
            }).forEach((pos) -> {
                Iterator var3 = Direction.Type.HORIZONTAL.iterator();

                while(var3.hasNext()) {
                    Direction direction = (Direction)var3.next();
                    if (random.nextFloat() <= 0.25F) {
                        Direction direction2 = direction.getOpposite();
                        BlockPos blockPos = pos.add(direction2.getOffsetX(), 0, direction2.getOffsetZ());
                        if (generator.isAir(blockPos)) {
                            generator.replace(blockPos, (BlockState)((BlockState) ModBlocks.SPORES.getDefaultState().with(SporesBlock.TEXTURE, random.nextBetween(0, 2))).with(SporesBlock.getProperty(direction), true));
                        }
                    }
                }

            });
        }
    }
}

//package io.github.trashoflevillage.mushroommadness.world.gen.decorators;
//
//import com.mojang.serialization.Codec;
//import com.mojang.serialization.MapCodec;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
//import io.github.trashoflevillage.mushroommadness.blocks.custom.SporesBlock;
//import io.github.trashoflevillage.mushroommadness.world.gen.ModTreeDecorator;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Direction;
//import net.minecraft.util.math.random.Random;
//import net.minecraft.world.gen.treedecorator.TreeDecorator;
//import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
//
//public class SporeDecorator extends TreeDecorator {
//    public static final MapCodec<SporeDecorator> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
//                    Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(p -> p.probability),
//                    Codec.intRange(0, 16).fieldOf("treeHeight").forGetter(p -> p.treeHeight))
//            .apply(instance, SporeDecorator::new));
//
//    private final float probability;
//    private final int treeHeight;
//
//    public SporeDecorator(float f, int treeHeight) {
//        this.probability = f;
//        this.treeHeight = treeHeight;
//    }
//
//    protected TreeDecoratorType<?> getType() { return ModTreeDecorator.SPORES; }
//
//    @Override
//    public void generate(Generator generator) {
//        Random random = generator.getRandom();
//        if (!(random.nextFloat() >= this.probability)) {
//            generator.getLogPositions().forEach(pos -> {
//                BlockPos blockPos;
//                if (random.nextInt(3) > 0 && generator.isAir(blockPos = pos.west())) {
//                    generator.replace(blockPos, ModBlocks.SPORES.getDefaultState().with(SporesBlock.getProperty(Direction.EAST), true));
//                }
//                if (random.nextInt(3) > 0 && generator.isAir(blockPos = pos.east())) {
//                    generator.replace(blockPos, ModBlocks.SPORES.getDefaultState().with(SporesBlock.getProperty(Direction.WEST), true));
//                }
//                if (random.nextInt(3) > 0 && generator.isAir(blockPos = pos.north())) {
//                    generator.replace(blockPos, ModBlocks.SPORES.getDefaultState().with(SporesBlock.getProperty(Direction.SOUTH), true));
//                }
//                if (random.nextInt(3) > 0 && generator.isAir(blockPos = pos.south())) {
//                    generator.replace(blockPos, ModBlocks.SPORES.getDefaultState().with(SporesBlock.getProperty(Direction.NORTH), true));
//                }
//            });
//        }
//    }
//}

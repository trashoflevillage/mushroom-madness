package io.github.trashoflevillage.mushroommadness.world.gen.decorators;

import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class BrownShelfMushroomDecorator extends AbstractShelfMushroomDecorator {
    public static TreeDecoratorType<BrownShelfMushroomDecorator> TYPE;

    public BrownShelfMushroomDecorator(float f) {
        super(f);
    }

    @Override
    public Block getShelfType() {
        return ModBlocks.BROWN_SHELF_MUSHROOM;
    }

    @Override
    public TreeDecoratorType<?> getType() {
        return TYPE;
    }

    @Override
    public void generate(Generator generator) {
        super.generate(generator);
    }
}

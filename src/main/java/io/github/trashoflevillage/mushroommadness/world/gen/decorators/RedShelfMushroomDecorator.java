package io.github.trashoflevillage.mushroommadness.world.gen.decorators;

import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class RedShelfMushroomDecorator extends AbstractShelfMushroomDecorator {
    public static TreeDecoratorType<RedShelfMushroomDecorator> TYPE;

    public RedShelfMushroomDecorator(float f) {
        super(f);
    }

    @Override
    public Block getShelfType() {
        return ModBlocks.RED_SHELF_MUSHROOM;
    }

    @Override
    public TreeDecoratorType<?> getType() {
        return TYPE;
    }
}

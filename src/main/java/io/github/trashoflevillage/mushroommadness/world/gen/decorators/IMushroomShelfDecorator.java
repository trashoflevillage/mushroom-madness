package io.github.trashoflevillage.mushroommadness.world.gen.decorators;

import net.minecraft.block.Block;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public interface IMushroomShelfDecorator {
    Block getShelfType();

    TreeDecoratorType<?> getType();
}

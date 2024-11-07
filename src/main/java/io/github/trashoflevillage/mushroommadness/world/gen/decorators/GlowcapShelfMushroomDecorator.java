package io.github.trashoflevillage.mushroommadness.world.gen.decorators;

import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class GlowcapShelfMushroomDecorator extends AbstractShelfMushroomDecorator {
    public static TreeDecoratorType<GlowcapShelfMushroomDecorator> TYPE;

    public GlowcapShelfMushroomDecorator(float f) {
        super(f);
    }

    @Override
    public Block getShelfType() {
        return ModBlocks.GLOWCAP_SHELF_MUSHROOM;
    }

    @Override
    public TreeDecoratorType<?> getType() {
        return TYPE;
    }
}

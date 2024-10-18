package io.github.trashoflevillage.mushroommadness.world.gen;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.mixin.TreeDecoratorTypeMixin;
import io.github.trashoflevillage.mushroommadness.world.gen.decorators.SporeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class ModTreeDecorator {
    public static final TreeDecoratorType<?> SPORES = TreeDecoratorTypeMixin.callRegister("spores", SporeDecorator.CODEC);

    public static void register() {
        MushroomMadness.LOGGER.info("Registering the Tree Decorator for " + MushroomMadness.MOD_ID);
    }
}

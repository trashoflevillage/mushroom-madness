package io.github.trashoflevillage.mushroommadness.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.world.gen.decorators.SporeDecorator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class ModTreeDecorator {

    public static void register() {
        SporeDecorator.TYPE = Registry.register(Registries.TREE_DECORATOR_TYPE, Identifier.of(MushroomMadness.MOD_ID, "spores"),
                new TreeDecoratorType<>(RecordCodecBuilder.mapCodec((instance) -> instance.group(Codec.floatRange(0.0F, 1.0F)
                        .fieldOf("probability").forGetter((SporeDecorator decorator) -> decorator.probability)).apply(instance, SporeDecorator::new))));
    }
}
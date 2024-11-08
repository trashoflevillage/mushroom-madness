package io.github.trashoflevillage.mushroommadness.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModConventionalTags {
    public static class Blocks {
        public static final TagKey<Block> STRIPPED_LOGS =
                createTag("stripped_logs");
        public static final TagKey<Block> STRIPPED_WOODS =
                createTag("stripped_woods");
        public static final TagKey<Block> WOODS =
                createTag("woods");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", name));
        }
    }
}

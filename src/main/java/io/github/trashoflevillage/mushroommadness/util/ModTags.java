package io.github.trashoflevillage.mushroommadness.util;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> SPORES_SPREADABLE =
                createTag("spores_spreadable");
        public static final TagKey<Block> SPOREWOOD_LOGS =
                createTag("sporewood_logs");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MushroomMadness.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> XP_COMPOSTABLE_SMALL =
                createTag("xp_compostable_small");
        public static final TagKey<Item> XP_COMPOSTABLE_MEDIUM =
                createTag("xp_compostable_medium");
        public static final TagKey<Item> XP_COMPOSTABLE_LARGE =
                createTag("xp_compostable_large");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MushroomMadness.MOD_ID, name));
        }
    }
}

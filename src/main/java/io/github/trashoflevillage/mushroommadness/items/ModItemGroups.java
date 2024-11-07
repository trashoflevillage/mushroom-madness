package io.github.trashoflevillage.mushroommadness.items;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModItemGroups {
    public static final ItemGroup MUSHROOM_MADNESS_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(MushroomMadness.MOD_ID, "mushroommadness"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.mushroommadness"))
                    .icon(() -> new ItemStack(ModItems.BOTTOMLESS_STEW))
                    .entries(((displayContext, entries) -> {
                            entries.add(ModBlocks.MYCELIUM_GROWTH);
                            entries.add(ModBlocks.SPORES);
                            entries.add(ModBlocks.DEAD_SPORES);
                            entries.add(ModBlocks.SPOREWOOD_LOG);
                            entries.add(ModBlocks.SPOREWOOD_WOOD);
                            entries.add(ModBlocks.STRIPPED_SPOREWOOD_LOG);
                            entries.add(ModBlocks.STRIPPED_SPOREWOOD_WOOD);
                            entries.add(ModBlocks.SPOREWOOD_PLANKS);
                            entries.add(ModBlocks.SPOREWOOD_STAIRS);
                            entries.add(ModBlocks.SPOREWOOD_SLAB);
                            entries.add(ModBlocks.SPOREWOOD_FENCE);
                            entries.add(ModBlocks.SPOREWOOD_FENCE_GATE);
                            entries.add(ModBlocks.SPOREWOOD_DOOR);
                            entries.add(ModBlocks.SPOREWOOD_TRAPDOOR);
                            entries.add(ModBlocks.SPOREWOOD_PRESSURE_PLATE);
                            entries.add(ModBlocks.SPOREWOOD_BUTTON);
                            entries.add(ModItems.SPOREWOOD_SIGN);
                            entries.add(ModItems.SPOREWOOD_HANGING_SIGN);
                            entries.add(ModItems.SPOREWOOD_BOAT);
                            entries.add(ModItems.SPOREWOOD_CHEST_BOAT);
                            entries.add(ModItems.MUSIC_DISC_LENTINULA_EDODES);
                            entries.add(ModItems.MYCOLOGIST_SPAWN_EGG);
                            entries.add(ModItems.BOTTOMLESS_STEW);
                            entries.add(ModBlocks.WAXED_BROWN_MUSHROOM);
                            entries.add(ModBlocks.WAXED_RED_MUSHROOM);
                            entries.add(ModBlocks.GLOWCAP);
                            entries.add(ModBlocks.WAXED_GLOWCAP);
                            entries.add(ModBlocks.WAXED_LIT_GLOWCAP);
                            entries.add(ModBlocks.GLOWCAP_MUSHROOM_BLOCK);
                            entries.add(ModBlocks.BROWN_SHELF_MUSHROOM);
                            entries.add(ModBlocks.RED_SHELF_MUSHROOM);
                            entries.add(ModBlocks.GLOWCAP_SHELF_MUSHROOM);
                    }))
                    .build()
    );

    public static void registerItemGroups() {
        addItemsToItemGroup(ItemGroups.NATURAL,
                ModBlocks.MYCELIUM_GROWTH,
                ModBlocks.SPORES,
                ModBlocks.DEAD_SPORES,
                ModBlocks.SPOREWOOD_LOG,
                ModBlocks.GLOWCAP,
                ModBlocks.WAXED_BROWN_MUSHROOM,
                ModBlocks.WAXED_RED_MUSHROOM,
                ModBlocks.WAXED_GLOWCAP,
                ModBlocks.WAXED_LIT_GLOWCAP,
                ModBlocks.GLOWCAP_MUSHROOM_BLOCK,
                ModBlocks.BROWN_SHELF_MUSHROOM,
                ModBlocks.RED_SHELF_MUSHROOM,
                ModBlocks.GLOWCAP_SHELF_MUSHROOM
        );

        addItemsToItemGroup(ItemGroups.BUILDING_BLOCKS,
                ModBlocks.SPOREWOOD_LOG,
                ModBlocks.SPOREWOOD_WOOD,
                ModBlocks.STRIPPED_SPOREWOOD_LOG,
                ModBlocks.STRIPPED_SPOREWOOD_WOOD,
                ModBlocks.SPOREWOOD_PLANKS,
                ModBlocks.SPOREWOOD_STAIRS,
                ModBlocks.SPOREWOOD_SLAB,
                ModBlocks.SPOREWOOD_FENCE,
                ModBlocks.SPOREWOOD_FENCE_GATE,
                ModBlocks.SPOREWOOD_DOOR,
                ModBlocks.SPOREWOOD_TRAPDOOR,
                ModBlocks.SPOREWOOD_PRESSURE_PLATE,
                ModBlocks.SPOREWOOD_BUTTON,
                ModBlocks.WAXED_BROWN_MUSHROOM,
                ModBlocks.WAXED_RED_MUSHROOM,
                ModBlocks.WAXED_GLOWCAP,
                ModBlocks.WAXED_LIT_GLOWCAP
        );

        addItemsToItemGroup(ItemGroups.FUNCTIONAL,
                ModBlocks.SPORES,
                ModItems.SPOREWOOD_SIGN,
                ModItems.SPOREWOOD_HANGING_SIGN
        );

        addItemsToItemGroup(ItemGroups.TOOLS,
                ModItems.SPOREWOOD_BOAT,
                ModItems.SPOREWOOD_CHEST_BOAT,
                ModItems.MUSIC_DISC_LENTINULA_EDODES
        );

        addItemsToItemGroup(ItemGroups.SPAWN_EGGS,
                ModItems.MYCOLOGIST_SPAWN_EGG
        );

        addItemsToItemGroup(ItemGroups.FOOD_AND_DRINK,
                ModItems.BOTTOMLESS_STEW
        );

        addItemsToItemGroup(ItemGroups.INGREDIENTS,
                ModBlocks.GLOWCAP
        );
    }

    private static void addItemsToItemGroup(RegistryKey<ItemGroup> group, ItemConvertible... items) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> {
            for (ItemConvertible i : items)
                content.add(i);
        });
    }
}

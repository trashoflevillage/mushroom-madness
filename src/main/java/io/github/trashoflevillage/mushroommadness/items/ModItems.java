package io.github.trashoflevillage.mushroommadness.items;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.entity.ModBoats;
import io.github.trashoflevillage.mushroommadness.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SPOREWOOD_SIGN = registerItem("sporewood_sign",
            new SignItem(new Item.Settings().maxCount(16), ModBlocks.SPOREWOOD_SIGN, ModBlocks.SPOREWOOD_WALL_SIGN));
    public static final Item SPOREWOOD_HANGING_SIGN = registerItem("sporewood_hanging_sign",
            new HangingSignItem(ModBlocks.SPOREWOOD_HANGING_SIGN, ModBlocks.SPOREWOOD_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

    public static final Item SPOREWOOD_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.SPOREWOOD_BOAT_ID, ModBoats.SPOREWOOD_BOAT_KEY, false);
    public static final Item SPOREWOOD_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.SPOREWOOD_CHEST_BOAT_ID, ModBoats.SPOREWOOD_BOAT_KEY, true);

    public static final Item MYCOLOGIST_SPAWN_EGG = registerItem("mycologist_spawn_egg",
            new SpawnEggItem(ModEntities.MYCOLOGIST, 9804699, 0xA81012, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MushroomMadness.MOD_ID, name), item);
    }
    
    public static void registerModItems() {
        MushroomMadness.LOGGER.info("Registering items for " + MushroomMadness.MOD_ID + ".");

        addItemsToItemGroup(ItemGroups.NATURAL,
                ModBlocks.MYCELIUM_GROWTH,
                ModBlocks.SPORES,
                ModBlocks.SPOREWOOD_LOG);

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
                ModBlocks.SPOREWOOD_BUTTON);

        addItemsToItemGroup(ItemGroups.FUNCTIONAL,
                ModItems.SPOREWOOD_SIGN,
                ModItems.SPOREWOOD_HANGING_SIGN,
                ModBlocks.XP_COMPOSTER);

        addItemsToItemGroup(ItemGroups.TOOLS,
                ModItems.SPOREWOOD_BOAT,
                ModItems.SPOREWOOD_CHEST_BOAT);

        addItemsToItemGroup(ItemGroups.SPAWN_EGGS,
                ModItems.MYCOLOGIST_SPAWN_EGG
        );
    }

    private static void addItemsToItemGroup(RegistryKey<ItemGroup> group, ItemConvertible... items) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> {
            for (ItemConvertible i : items) content.add(i);
        });
    }
}
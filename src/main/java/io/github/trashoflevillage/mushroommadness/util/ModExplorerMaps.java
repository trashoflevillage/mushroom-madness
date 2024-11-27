package io.github.trashoflevillage.mushroommadness.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.MapColorComponent;
import net.minecraft.component.type.MapDecorationsComponent;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapDecorationTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.BlockPos;

public class ModExplorerMaps {
    public static ItemStack getMycologistTowerExplorerMap(BlockPos origin, ServerWorld world) {
        BlockPos pos = world.locateStructure(
                ModTags.Structures.ON_MYCOLOGIST_TOWER_MAPS,
                origin,
                100000,
                false
        );
        if (pos == null) return Items.MAP.getDefaultStack();
        ItemStack map = FilledMapItem.createMap(world, pos.getX(), pos.getZ(), (byte) 2, true, true);
        FilledMapItem.fillExplorationMap(world, map);
        MapDecorationsComponent.Decoration decor = new MapDecorationsComponent.Decoration(
                ModMapDecorationTypes.MYCOLOGIST_TOWER,
                pos.getX(),
                pos.getZ(),
                180f
        );
        MapDecorationsComponent newComponent = MapDecorationsComponent.DEFAULT.with("+", decor);
        map.set(DataComponentTypes.MAP_DECORATIONS, newComponent);
        map.set(DataComponentTypes.MAP_COLOR, new MapColorComponent(0xed452b));
        map.set(DataComponentTypes.ITEM_NAME, Text.translatable("filled_map.mycologist_tower"));
        return map;
    }
}

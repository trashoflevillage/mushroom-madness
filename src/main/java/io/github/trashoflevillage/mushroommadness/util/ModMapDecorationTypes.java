package io.github.trashoflevillage.mushroommadness.util;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import net.minecraft.block.MapColor;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModMapDecorationTypes {
    public static final RegistryEntry<MapDecorationType> MYCOLOGIST_TOWER = register(
            "mycologist_tower", "mycologist_tower", true, MapColor.RED.color, false, true
    );

    private static RegistryEntry<MapDecorationType> register(String id, String assetId, boolean showOnItemFrame, int mapColor, boolean trackCount, boolean explorationMapElement) {
        RegistryKey<MapDecorationType> registryKey = RegistryKey.of(RegistryKeys.MAP_DECORATION_TYPE, Identifier.of(MushroomMadness.MOD_ID, id));
        MapDecorationType mapDecorationType = new MapDecorationType(Identifier.ofVanilla(assetId), showOnItemFrame, mapColor, explorationMapElement, trackCount);
        return Registry.registerReference(Registries.MAP_DECORATION_TYPE, registryKey, mapDecorationType);
    }

    public static void registerMapDecorations() {

    }
}
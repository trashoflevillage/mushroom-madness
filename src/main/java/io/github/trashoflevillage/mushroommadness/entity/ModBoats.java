package io.github.trashoflevillage.mushroommadness.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.items.ModItems;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBoats {
    public static final Identifier SPOREWOOD_BOAT_ID = Identifier.of(MushroomMadness.MOD_ID, "sporewood_boat");
    public static final Identifier SPOREWOOD_CHEST_BOAT_ID = Identifier.of(MushroomMadness.MOD_ID, "sporewood_chest_boat");

    public static final RegistryKey<TerraformBoatType> SPOREWOOD_BOAT_KEY = TerraformBoatTypeRegistry.createKey(SPOREWOOD_BOAT_ID);

    public static void registerBoats() {
        TerraformBoatType sporewoodBoat = new TerraformBoatType.Builder()
                .item(ModItems.SPOREWOOD_BOAT)
                .chestItem(ModItems.SPOREWOOD_CHEST_BOAT)
                .planks(ModBlocks.SPOREWOOD_PLANKS.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, SPOREWOOD_BOAT_KEY, sporewoodBoat);
    }
}

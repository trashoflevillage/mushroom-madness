package io.github.trashoflevillage.mushroommadness.items;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.entity.ModBoats;
import io.github.trashoflevillage.mushroommadness.entity.ModEntities;
import io.github.trashoflevillage.mushroommadness.items.custom.CustomItem;
import io.github.trashoflevillage.mushroommadness.items.custom.MushroomBowItem;
import io.github.trashoflevillage.mushroommadness.sounds.ModSounds;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final Item SPOREWOOD_SIGN = registerItem("sporewood_sign",
            new SignItem(new Item.Settings().maxCount(16), ModBlocks.SPOREWOOD_SIGN, ModBlocks.SPOREWOOD_WALL_SIGN));
    public static final Item SPOREWOOD_HANGING_SIGN = registerItem("sporewood_hanging_sign",
            new HangingSignItem(ModBlocks.SPOREWOOD_HANGING_SIGN, ModBlocks.SPOREWOOD_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

    public static final Item SPOREWOOD_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.SPOREWOOD_BOAT_ID, ModBoats.SPOREWOOD_BOAT_KEY, false);
    public static final Item SPOREWOOD_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.SPOREWOOD_CHEST_BOAT_ID, ModBoats.SPOREWOOD_BOAT_KEY, true);

    public static final Item MYCOLOGIST_SPAWN_EGG = registerItem("mycologist_spawn_egg",
            new SpawnEggItem(ModEntities.MYCOLOGIST, 9804699, 0xA81012, new Item.Settings()));
    
    public static final Item MUSIC_DISC_LENTINULA_EDODES = registerItem("music_disc_lentinula_edodes",
            new Item(new Item.Settings().maxCount(1).jukeboxPlayable(ModSounds.of("lentinula_edodes")).rarity(Rarity.RARE)));

    public static final Item BOTTOMLESS_STEW = registerItem("bottomless_stew",
            new CustomItem(new Item.Settings().maxCount(1).food(
                    new FoodComponent.Builder().nutrition(6).saturationModifier(0.6F).build()
            ).rarity(Rarity.RARE))
                    .setCooldown(600)
                    .setCooldownApplication(CustomItem.CooldownApplication.FINISH_USING)
                    .setConsumedOnEat(false));

    public static final Item FUNGAL_UPGRADE_SMITHING_TEMPLATE = registerItem("fungal_upgrade_smithing_template",
            new SmithingTemplateItem(
                Text.translatable("item.mushroommadness.fungal_upgrade_smithing_template.applies_to"),
                Text.translatable("item.mushroommadness.fungal_upgrade_smithing_template.ingredients"),
                Text.translatable("upgrade.mushroommadness.fungal_upgrade"),
                Text.translatable("item.mushroommadness.fungal_upgrade_smithing_template.base_slot_description"),
                Text.translatable("item.mushroommadness.fungal_upgrade_smithing_template.additions_slot_description"),
                getFungalUpgradeEmptyBaseSlotTextures(),
                getFungalUpgradeEmptyAdditionsSlotTextures()
            )
    );

    private static final int MUSHROOM_BOW_DURABILITY = 384;

    public static final Item RED_MUSHROOM_BOW = registerItem("red_mushroom_bow",
            new MushroomBowItem(new Item.Settings()
                    .maxCount(1)
                    .maxDamage(MUSHROOM_BOW_DURABILITY),
                    new RegistryEntry[] {
                            StatusEffects.POISON
                    }, 100)
            );

    public static final Item BROWN_MUSHROOM_BOW = registerItem("brown_mushroom_bow",
            new MushroomBowItem(new Item.Settings()
                    .maxCount(1)
                    .maxDamage(MUSHROOM_BOW_DURABILITY),
                    new RegistryEntry[]{
                            StatusEffects.WEAKNESS
                    }, 100)
    );

    public static final Item GLOWCAP_MUSHROOM_BOW = registerItem("glowcap_mushroom_bow",
            new MushroomBowItem(new Item.Settings()
                    .maxCount(1)
                    .maxDamage(MUSHROOM_BOW_DURABILITY),
                    new RegistryEntry[]{
                            StatusEffects.GLOWING
                    }, 100)
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MushroomMadness.MOD_ID, name), item);
    }
    
    public static void registerModItems() {
        MushroomMadness.LOGGER.info("Registering items for " + MushroomMadness.MOD_ID + ".");
    }

    private static List<Identifier> getFungalUpgradeEmptyBaseSlotTextures() {
        return List.of(
                Identifier.of(MushroomMadness.MOD_ID, "item/empty_slot_bow")
        );
    }

    private static List<Identifier> getFungalUpgradeEmptyAdditionsSlotTextures() {
        return List.of(
                Identifier.of(MushroomMadness.MOD_ID, "item/empty_slot_mushroom"),
                Identifier.of(MushroomMadness.MOD_ID, "item/empty_slot_glowcap")
        );
    }
}
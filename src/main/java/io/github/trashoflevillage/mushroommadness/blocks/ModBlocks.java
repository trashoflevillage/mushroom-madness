package io.github.trashoflevillage.mushroommadness.blocks;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.blocks.custom.CustomShortPlantBlock;
import io.github.trashoflevillage.mushroommadness.blocks.custom.SporesBlock;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block MYCELIUM_GROWTH =
            registerBlock("mycelium_growth",
                    new CustomShortPlantBlock(AbstractBlock.Settings.create()
                            .mapColor(MapColor.PURPLE)
                            .replaceable()
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.GRASS)
                            .offset(AbstractBlock.OffsetType.XYZ)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block SPORES =
            registerBlock("spores",
                    new SporesBlock(AbstractBlock.Settings.create()
                            .mapColor(MapColor.PURPLE)
                            .replaceable()
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.WEEPING_VINES)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .ticksRandomly()));

    public static final Block SPOREWOOD_LOG =
            registerBlock("sporewood_log",
                    new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block SPOREWOOD_WOOD =
            registerBlock("sporewood_wood",
                    new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_SPOREWOOD_LOG =
            registerBlock("stripped_sporewood_log",
                    new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_SPOREWOOD_WOOD =
            registerBlock("stripped_sporewood_wood",
                    new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block SPOREWOOD_PLANKS =
            registerBlock("sporewood_planks",
                    new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));

    public static final Identifier SPOREWOOD_SIGN_TEXTURE = Identifier.of(MushroomMadness.MOD_ID, "entity/signs/sporewood");
    public static final Identifier SPOREWOOD_HANGING_SIGN_TEXTURE = Identifier.of(MushroomMadness.MOD_ID, "entity/signs/hanging/sporewood");
    public static final Identifier SPOREWOOD_HANGING_GUI_SIGN_TEXTURE = Identifier.of(MushroomMadness.MOD_ID, "textures/gui/hanging_signs/sporewood");

    public static final Block SPOREWOOD_SIGN =
            registerBlock("sporewood_sign",
                    new TerraformSignBlock(SPOREWOOD_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_SIGN)));
    public static final Block SPOREWOOD_WALL_SIGN =
            registerBlock("sporewood_wall_sign",
                    new TerraformSignBlock(SPOREWOOD_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)));
    public static final Block SPOREWOOD_HANGING_SIGN =
            registerBlock("sporewood_hanging_sign",
                    new TerraformHangingSignBlock(SPOREWOOD_HANGING_SIGN_TEXTURE, SPOREWOOD_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)));
    public static final Block SPOREWOOD_WALL_HANGING_SIGN =
            registerBlock("sporewood_wall_hanging_sign",
                    new TerraformHangingSignBlock(SPOREWOOD_HANGING_SIGN_TEXTURE, SPOREWOOD_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final BlockFamily SPOREWOOD_FAMILY = BlockFamilies.register(ModBlocks.SPOREWOOD_PLANKS)
            .sign(ModBlocks.SPOREWOOD_SIGN, ModBlocks.SPOREWOOD_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();

    private static Block registerBlock(String name, Block block) {
        return registerBlock(name, block, true);
    }

    private static Block registerBlock(String name, Block block, boolean hasBlockItem) {
        if (hasBlockItem) registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK,
                Identifier.of(MushroomMadness.MOD_ID, name),
                block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(MushroomMadness.MOD_ID, name),
                new BlockItem(block,
                        new Item.Settings()));
    }

    public static void registerModBlocks() {
        MushroomMadness.LOGGER.info("Registering blocks for " + MushroomMadness.MOD_ID);
    }
}

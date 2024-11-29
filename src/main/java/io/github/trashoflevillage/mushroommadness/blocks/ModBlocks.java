package io.github.trashoflevillage.mushroommadness.blocks;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.blocks.custom.*;
import io.github.trashoflevillage.mushroommadness.world.ModConfiguredFeatures;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoneycombItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;

import java.util.function.ToIntFunction;

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
                            .ticksRandomly()
                    ));

    public static final Block DEAD_SPORES =
            registerBlock("dead_spores",
                    new SporesBlock(AbstractBlock.Settings.create()
                            .mapColor(MapColor.GRAY)
                            .replaceable()
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.WEEPING_VINES)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY),
                            false
                    ));

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

    public static final Block SPOREWOOD_STAIRS =
            registerBlock("sporewood_stairs",
                    new StairsBlock(ModBlocks.SPOREWOOD_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));
    public static final Block SPOREWOOD_SLAB =
            registerBlock("sporewood_slab",
                    new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));

    public static final Block SPOREWOOD_BUTTON =
            registerBlock("sporewood_button",
                    new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    public static final Block SPOREWOOD_PRESSURE_PLATE =
            registerBlock("sporewood_pressure_plate",
                    new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));

    public static final Block SPOREWOOD_FENCE =
            registerBlock("sporewood_fence",
                    new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    public static final Block SPOREWOOD_FENCE_GATE =
            registerBlock("sporewood_fence_gate",
                    new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)));

    public static final Block SPOREWOOD_DOOR =
            registerBlock("sporewood_door",
                    new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    public static final Block SPOREWOOD_TRAPDOOR =
            registerBlock("sporewood_trapdoor",
                    new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));

    public static final Block SPOREWOOD_SIGN =
            registerBlock("sporewood_sign",
                    new TerraformSignBlock(SPOREWOOD_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_SIGN)));
    public static final Block SPOREWOOD_WALL_SIGN =
            registerBlock("sporewood_wall_sign",
                    new TerraformWallSignBlock(SPOREWOOD_SIGN_TEXTURE, AbstractBlock.Settings.copy(ModBlocks.SPOREWOOD_SIGN)));
    public static final Block SPOREWOOD_HANGING_SIGN =
            registerBlock("sporewood_hanging_sign",
                    new TerraformHangingSignBlock(SPOREWOOD_HANGING_SIGN_TEXTURE, SPOREWOOD_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)));
    public static final Block SPOREWOOD_WALL_HANGING_SIGN =
            registerBlock("sporewood_wall_hanging_sign",
                    new TerraformWallHangingSignBlock(SPOREWOOD_HANGING_SIGN_TEXTURE, SPOREWOOD_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.copy(ModBlocks.SPOREWOOD_HANGING_SIGN)));

    public static final BlockFamily SPOREWOOD_FAMILY = BlockFamilies.register(ModBlocks.SPOREWOOD_PLANKS)
            .sign(ModBlocks.SPOREWOOD_SIGN, ModBlocks.SPOREWOOD_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();

    public static final Block GLOWCAP =
            registerBlock("glowcap",
                    new GlowcapBlock(
                            ModConfiguredFeatures.HUGE_GLOWCAP_KEY,
                            AbstractBlock.Settings.create()
                                    .mapColor(MapColor.PALE_YELLOW)
                                    .noCollision()
                                    .ticksRandomly()
                                    .breakInstantly()
                                    .sounds(BlockSoundGroup.GRASS)
                                    .postProcess(Blocks::always)
                                    .pistonBehavior(PistonBehavior.DESTROY)
                                    .emissiveLighting(Blocks::always)
                                    .luminance(createLightLevelFromLitBlockState(10, 4))
                    ));
    public static final Block POTTED_GLOWCAP =
            registerBlock("potted_glowcap",
                    new FlowerPotBlock(ModBlocks.GLOWCAP,
                            AbstractBlock.Settings.copy(Blocks.POTTED_BROWN_MUSHROOM)
                                .emissiveLighting(Blocks::always)
                                .mapColor(MapColor.PALE_YELLOW)
                                    .luminance((state) -> {
                                        return 4;
                                    })
                    ));

    public static final Block GLOWCAP_MUSHROOM_BLOCK =
            registerBlock("glowcap_mushroom_block",
                    new GlowcapMushroomBlock(
                            AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM_BLOCK)
                                    .mapColor(MapColor.PALE_YELLOW)
                                    .ticksRandomly()
                                    .emissiveLighting(Blocks::always)
                                    .luminance(state -> 10)
                    ));

    public static final Block WAXED_GLOWCAP =
            registerBlock("waxed_glowcap",
                    new WaxedGlowcapBlock(
                            ModConfiguredFeatures.HUGE_GLOWCAP_KEY,
                            AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW)
                                    .noCollision()
                                    .ticksRandomly()
                                    .breakInstantly()
                                    .sounds(BlockSoundGroup.GRASS)
                                    .postProcess(Blocks::always)
                                    .pistonBehavior(PistonBehavior.DESTROY)
                                    .emissiveLighting(Blocks::always)
                                    .luminance((state) -> {
                                        return 4;
                                    }), false
                    ));
    public static final Block POTTED_WAXED_GLOWCAP =
            registerBlock("potted_waxed_glowcap",
                    new FlowerPotBlock(ModBlocks.WAXED_GLOWCAP,
                            AbstractBlock.Settings.copy(ModBlocks.POTTED_GLOWCAP)
                                    .luminance((state) -> {
                                        return 4;
                                    })
                    ));

    public static final Block WAXED_LIT_GLOWCAP =
            registerBlock("waxed_lit_glowcap",
                    new WaxedGlowcapBlock(
                            ModConfiguredFeatures.HUGE_GLOWCAP_KEY,
                            AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW)
                                    .noCollision()
                                    .ticksRandomly()
                                    .breakInstantly()
                                    .sounds(BlockSoundGroup.GRASS)
                                    .postProcess(Blocks::always)
                                    .pistonBehavior(PistonBehavior.DESTROY)
                                    .emissiveLighting(Blocks::always)
                                    .luminance((state) -> {
                                        return 10;
                                    }), true
                    ));
    public static final Block POTTED_WAXED_LIT_GLOWCAP =
            registerBlock("potted_waxed_lit_glowcap",
                    new LitGlowcapPotBlock(ModBlocks.WAXED_LIT_GLOWCAP,
                            AbstractBlock.Settings.copy(ModBlocks.POTTED_GLOWCAP)
                                    .luminance((state) -> {
                                        return 10;
                                    })
                    ));

    public static final Block WAXED_BROWN_MUSHROOM =
            registerBlock("waxed_brown_mushroom",
                    new WaxedMushroomBlock(
                            TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM,
                            AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM)
                    )
                );

    public static final Block WAXED_RED_MUSHROOM =
            registerBlock("waxed_red_mushroom",
                    new WaxedMushroomBlock(
                            TreeConfiguredFeatures.HUGE_RED_MUSHROOM,
                            AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM)
                    )
            );

    public static final Block BROWN_SHELF_MUSHROOM =
            registerBlock("brown_shelf_mushroom",
                    new MushroomShelfBlock(AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM_BLOCK)
                            .mapColor(MapColor.BROWN)
                            .breakInstantly()
                            .pistonBehavior(PistonBehavior.DESTROY)
                    )
            );

    public static final Block RED_SHELF_MUSHROOM =
            registerBlock("red_shelf_mushroom",
                    new MushroomShelfBlock(AbstractBlock.Settings.copy(BROWN_SHELF_MUSHROOM)
                            .mapColor(MapColor.RED)
                    )
            );

    public static final Block GLOWCAP_SHELF_MUSHROOM =
            registerBlock("glowcap_shelf_mushroom",
                    new MushroomShelfBlock(AbstractBlock.Settings.copy(BROWN_SHELF_MUSHROOM)
                            .emissiveLighting(Blocks::always)
                            .luminance((state) -> {
                                return 10;
                            })
                            .mapColor(MapColor.PALE_YELLOW)
                    )
            );

//    public static final Block GLOWCAP_ROOTS = registerBlock("glowcap_roots",
//            new GlowcapRootsBlock(
//                    AbstractBlock.Settings.copy(Blocks.HANGING_ROOTS)
//                            .luminance((state) -> {
//                                return 7;
//                            })
//                            .ticksRandomly()
//            )
//    );

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

    private static Block registerBlock(String name, Block block, Item.Settings itemSettings) {
        registerBlockItem(name, block, itemSettings);
        return Registry.register(Registries.BLOCK,
                Identifier.of(MushroomMadness.MOD_ID, name),
                block);
    }

    private static Item registerBlockItem(String name, Block block, Item.Settings settings) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(MushroomMadness.MOD_ID, name),
                new BlockItem(block,
                        settings));
    }

    public static void registerModBlocks() {
        MushroomMadness.LOGGER.info("Registering blocks for " + MushroomMadness.MOD_ID);

        addWaxingRecipe(Blocks.BROWN_MUSHROOM, ModBlocks.WAXED_BROWN_MUSHROOM);
        addWaxingRecipe(Blocks.RED_MUSHROOM, ModBlocks.WAXED_RED_MUSHROOM);
    }

    private static void addWaxingRecipe(Block from, Block to) {
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().put(from, to);
    }

    public static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return createLightLevelFromLitBlockState(litLevel, 0);
    }

    public static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel, int unlitLevel) {
        return (state) -> {
            return (Boolean)state.get(Properties.LIT) ? litLevel : unlitLevel;
        };
    }
}

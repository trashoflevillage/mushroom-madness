package io.github.trashoflevillage.mushroommadness;

import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.items.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MushroomMadness implements ModInitializer {
	public static final String MOD_ID = "mushroommadness";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();

		StrippableBlockRegistry.register(ModBlocks.SPOREWOOD_LOG, ModBlocks.STRIPPED_SPOREWOOD_LOG);
		StrippableBlockRegistry.register(ModBlocks.SPOREWOOD_WOOD, ModBlocks.STRIPPED_SPOREWOOD_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.SPOREWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.SPOREWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_SPOREWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_SPOREWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.SPOREWOOD_PLANKS, 5, 20);
	}
}
package io.github.trashoflevillage.mushroommadness.client;

import io.github.trashoflevillage.mushroommadness.items.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModModelProvider {
    public static void registerBowModels() {
        registerBowModel(ModItems.RED_MUSHROOM_BOW);
        registerBowModel(ModItems.BROWN_MUSHROOM_BOW);
    }

    private static void registerBowModel(Item bow) {
        ModelPredicateProviderRegistry.register(bow, Identifier.of("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (entity.getActiveItem() != stack) {
                return 0.0f;
            }
            return (float) (stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0f;
        });
        ModelPredicateProviderRegistry.register(bow, Identifier.of("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0f : 0.0f);

    }
}

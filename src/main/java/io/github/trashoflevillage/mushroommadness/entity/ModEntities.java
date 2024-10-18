package io.github.trashoflevillage.mushroommadness.entity;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.entity.custom.MushroomZombieEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MushroomZombieEntity> MUSHROOM_ZOMBIE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MushroomMadness.MOD_ID, "mushroom_zombie"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MushroomZombieEntity::new)
                    .dimensions(EntityDimensions.fixed(1.95f, 0.6f))
                    .build());
}

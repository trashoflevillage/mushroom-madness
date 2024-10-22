package io.github.trashoflevillage.mushroommadness.entity;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.entity.custom.MycologistEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MycologistEntity> MYCOLOGIST = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MushroomMadness.MOD_ID, "mycologist"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MycologistEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f))
                    .build());
}

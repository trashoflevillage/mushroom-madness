package io.github.trashoflevillage.mushroommadness.entity;

import io.github.trashoflevillage.mushroommadness.MushroomMadness;
import io.github.trashoflevillage.mushroommadness.entity.custom.MushroomArrowEntity;
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

    public static final EntityType<MushroomArrowEntity> MUSHROOM_ARROW = Registry.register(
            Registries.ENTITY_TYPE,
           Identifier.of(MushroomMadness.MOD_ID, "mushroom_arrow"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, MushroomArrowEntity::newRedMushroomArrow)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
                    .trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
                    .build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
    );
}

package io.github.trashoflevillage.mushroommadness.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class MushroomZombieEntity extends ZombieEntity {
    public MushroomZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean canTarget(EntityType<?> type) {
        return false;
    }
}

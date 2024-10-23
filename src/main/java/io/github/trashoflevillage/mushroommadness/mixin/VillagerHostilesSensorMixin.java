package io.github.trashoflevillage.mushroommadness.mixin;


import com.google.common.collect.ImmutableMap;
import io.github.trashoflevillage.mushroommadness.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.NearestVisibleLivingEntitySensor;
import net.minecraft.entity.ai.brain.sensor.VillagerHostilesSensor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(VillagerHostilesSensor.class)
public abstract class VillagerHostilesSensorMixin extends NearestVisibleLivingEntitySensor {
    @Shadow @Final @Mutable private static ImmutableMap<EntityType<?>, Float> SQUARED_DISTANCES_FOR_DANGER;

    static {
        SQUARED_DISTANCES_FOR_DANGER = ImmutableMap.<EntityType<?>, Float>builder()
                .putAll(SQUARED_DISTANCES_FOR_DANGER.entrySet())
                .put(ModEntities.MYCOLOGIST, 12.0F)
                .build();
    }
}

package io.github.trashoflevillage.mushroommadness.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MushroomArrowEntity extends ArrowEntity {
    private static final TrackedData<String> TYPE = DataTracker.registerData(MushroomArrowEntity.class, TrackedDataHandlerRegistry.STRING);

    public MushroomArrowEntity(EntityType<? extends ArrowEntity> entityType, World world, MushroomArrowType type) {
        super(entityType, world);
        this.setMushroomType(type);
    }

    public MushroomArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom, MushroomArrowType type) {
        super(world, owner, stack, shotFrom);
        this.setMushroomType(type);
    }

    public static MushroomArrowEntity newRedMushroomArrow(EntityType<? extends ArrowEntity> entityType, World world) {
        return new MushroomArrowEntity(entityType, world, MushroomArrowType.RED);
    }

    public MushroomArrowType getMushroomType() {
        return MushroomArrowType.fromString(this.dataTracker.get(TYPE));
    }

    public void setMushroomType(MushroomArrowType type) {
        this.dataTracker.set(TYPE, type.asString());
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Type", this.getMushroomType().asString());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setMushroomType((MushroomArrowType.fromString(nbt.getString("Type"))));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TYPE, MushroomArrowType.RED.asString());
    }

    public enum MushroomArrowType {
        RED, BROWN, GLOWCAP;

        public static MushroomArrowType fromString(String str) {
            switch (str) {
                case "red": return RED;
                case "brown": return BROWN;
                case "glowcap": return GLOWCAP;
            }
            return RED;
        }

        public String asString() {
            switch (this) {
                case RED: return "red";
                case BROWN: return "brown";
                case GLOWCAP: return "glowcap";
            }
            return "red";
        }
    }
}

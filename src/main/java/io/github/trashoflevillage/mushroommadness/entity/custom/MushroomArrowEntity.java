package io.github.trashoflevillage.mushroommadness.entity.custom;

import io.github.trashoflevillage.mushroommadness.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MushroomArrowEntity extends PersistentProjectileEntity {
    private static final TrackedData<Integer> TYPE = DataTracker.registerData(MushroomArrowEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public MushroomArrowEntity(EntityType<? extends MushroomArrowEntity> entityType, World world) {
        super(entityType, world);
        this.dataTracker.set(TYPE, 1);
    }

    public MushroomArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom, Integer type) {
        super(ModEntities.MUSHROOM_ARROW, x, y+1.6, z, world, stack, shotFrom);
        this.dataTracker.set(TYPE, type);
    }

    public MushroomArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom, MushroomArrowType type) {
        super(ModEntities.MUSHROOM_ARROW, x, y+1.6, z, world, stack, shotFrom);
        this.dataTracker.set(TYPE, type.index);
    }


    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.ARROW.getDefaultStack();
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TYPE, 1);
    }

    public int getMushroomType() {
        return this.dataTracker.get(TYPE);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        if (getMushroomType() == MushroomArrowType.RED.getIndex())
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200));
        else if (getMushroomType() == MushroomArrowType.BROWN.getIndex())
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200));
        else if (getMushroomType() == MushroomArrowType.GLOWCAP.getIndex())
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200));
    }

    public enum MushroomArrowType {
        RED(1),
        BROWN(2),
        GLOWCAP(3);

        private final int index;
        MushroomArrowType(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}
package io.github.trashoflevillage.mushroommadness.entity.custom;

import io.github.trashoflevillage.mushroommadness.entity.ModEntities;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
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
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MushroomArrowEntity extends PersistentProjectileEntity {
    private static final TrackedData<Integer> TYPE = DataTracker.registerData(MushroomArrowEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final int MAX_POTION_DURATION_TICKS = 600;
    private static final int NO_POTION_COLOR = -1;
    private static final TrackedData<Integer> COLOR = DataTracker.registerData(MushroomArrowEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final byte PARTICLE_EFFECT_STATUS = 0;

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


    private PotionContentsComponent getPotionContents() {
        return this.getItemStack().getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
    }

    private void setPotionContents(PotionContentsComponent potionContentsComponent) {
        this.getItemStack().set(DataComponentTypes.POTION_CONTENTS, potionContentsComponent);
        this.initColor();
    }

    @Override
    protected void setStack(ItemStack stack) {
        super.setStack(stack);
        this.initColor();
    }

    private void initColor() {
        PotionContentsComponent potionContentsComponent = this.getPotionContents();
        this.dataTracker.set(COLOR, potionContentsComponent.equals(PotionContentsComponent.DEFAULT) ? -1 : potionContentsComponent.getColor());
    }

    public void addEffect(StatusEffectInstance effect) {
        this.setPotionContents(this.getPotionContents().with(effect));
    }


    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.ARROW.getDefaultStack();
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TYPE, 1);
        builder.add(COLOR, -1);
    }

    public int getMushroomType() {
        return this.dataTracker.get(TYPE);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);

        // from vanilla ArrowEntity class
        Entity entity = this.getEffectCause();
        PotionContentsComponent potionContentsComponent = this.getPotionContents();
        if (potionContentsComponent.potion().isPresent()) {
            for (StatusEffectInstance statusEffectInstance : ((Potion)((RegistryEntry)potionContentsComponent.potion().get()).value()).getEffects()) {
                target.addStatusEffect(
                        new StatusEffectInstance(
                                statusEffectInstance.getEffectType(),
                                Math.max(statusEffectInstance.mapDuration(i -> i / 8), 1),
                                statusEffectInstance.getAmplifier(),
                                statusEffectInstance.isAmbient(),
                                statusEffectInstance.shouldShowParticles()
                        ),
                        entity
                );
            }
        }

        for (StatusEffectInstance statusEffectInstance : potionContentsComponent.customEffects()) {
            target.addStatusEffect(statusEffectInstance, entity);
        }

        if (getMushroomType() == MushroomArrowType.RED.getIndex())
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200));
        else if (getMushroomType() == MushroomArrowType.BROWN.getIndex())
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200));
        else if (getMushroomType() == MushroomArrowType.GLOWCAP.getIndex())
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.spawnParticles(1);
                }
            } else {
                this.spawnParticles(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && !this.getPotionContents().equals(PotionContentsComponent.DEFAULT) && this.inGroundTime >= 600) {
            this.getWorld().sendEntityStatus(this, (byte)0);
            this.setStack(new ItemStack(Items.ARROW));
        }
    }

    private void spawnParticles(int amount) {
        int i = this.getColor();
        if (i != -1 && amount > 0) {
            for (int j = 0; j < amount; j++) {
                this.getWorld()
                        .addParticle(
                                EntityEffectParticleEffect.create(ParticleTypes.ENTITY_EFFECT, i), this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), 0.0, 0.0, 0.0
                        );
            }
        }
    }

    public int getColor() {
        return this.dataTracker.get(COLOR);
    }


    @Override
    public void handleStatus(byte status) {
        if (status == 0) {
            int i = this.getColor();
            if (i != -1) {
                float f = (float)(i >> 16 & 0xFF) / 255.0F;
                float g = (float)(i >> 8 & 0xFF) / 255.0F;
                float h = (float)(i >> 0 & 0xFF) / 255.0F;

                for (int j = 0; j < 20; j++) {
                    this.getWorld()
                            .addParticle(
                                    EntityEffectParticleEffect.create(ParticleTypes.ENTITY_EFFECT, f, g, h),
                                    this.getParticleX(0.5),
                                    this.getRandomBodyY(),
                                    this.getParticleZ(0.5),
                                    0.0,
                                    0.0,
                                    0.0
                            );
                }
            }
        } else {
            super.handleStatus(status);
        }
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

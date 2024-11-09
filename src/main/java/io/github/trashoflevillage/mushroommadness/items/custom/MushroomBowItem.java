package io.github.trashoflevillage.mushroommadness.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;

public class MushroomBowItem extends BowItem {
    private final RegistryEntry<StatusEffect> statusEffect;
    private final int statusEffectDuration;

    public MushroomBowItem(Settings settings, RegistryEntry<StatusEffect> statusEffect, int statusEffectDuration) {
        super(settings);
        this.statusEffect = statusEffect;
        this.statusEffectDuration = statusEffectDuration;
    }

    @Override
    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
        super.shoot(shooter, projectile, index, speed, divergence, yaw, target);
        ((ArrowEntity)projectile).addEffect(new StatusEffectInstance(statusEffect, statusEffectDuration));
        ((ArrowEntity)projectile).pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
    }
}

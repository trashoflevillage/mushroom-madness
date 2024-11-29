package io.github.trashoflevillage.mushroommadness.items.custom;

import io.github.trashoflevillage.mushroommadness.entity.custom.MushroomArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MushroomBowItem extends BowItem {
    private final MushroomArrowEntity.MushroomArrowType arrowType;

    public MushroomBowItem(Settings settings, MushroomArrowEntity.MushroomArrowType arrowType) {
        super(settings);
        this.arrowType = arrowType;
    }

    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        MushroomArrowEntity persistentProjectileEntity = new MushroomArrowEntity(world, shooter, projectileStack.copyWithCount(1), weaponStack, arrowType);
        if (critical) {
            persistentProjectileEntity.setCritical(true);
        }

        return persistentProjectileEntity;
    }
}

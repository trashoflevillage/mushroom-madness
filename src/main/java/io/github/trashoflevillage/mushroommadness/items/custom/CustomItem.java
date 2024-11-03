package io.github.trashoflevillage.mushroommadness.items.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CustomItem extends Item {
    private int cooldown;
    private CooldownApplication cooldownApplication = CooldownApplication.ON_USE;
    private boolean consumedOnEat = true;

    public CustomItem(Settings settings) {
        super(settings);
    }

    public CustomItem setCooldown(int cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    public CustomItem setCooldownApplication(CooldownApplication cooldownApplication) {
        this.cooldownApplication = cooldownApplication;
        return this;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (cooldownApplication == CooldownApplication.ON_USE) user.getItemCooldownManager().set(this, cooldown);
        return super.use(world, user, hand);
    }

    public CustomItem setConsumedOnEat(boolean consumedOnEat) {
        this.consumedOnEat = consumedOnEat;
        return this;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity && cooldownApplication == CooldownApplication.FINISH_USING) ((PlayerEntity)user).getItemCooldownManager().set(this, cooldown);
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);

        if (consumedOnEat)
            return foodComponent != null ? user.eatFood(world, stack, foodComponent) : stack;
        else {
            ItemStack outputStack = stack.copy();
            user.eatFood(world, stack, foodComponent);
            return outputStack;
        }
    }

    public enum CooldownApplication {
        ON_USE,
        FINISH_USING
    }
}

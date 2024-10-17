package io.github.trashoflevillage.mushroommadness.blocks.custom;

import io.github.trashoflevillage.mushroommadness.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class XPComposterBlock extends Block {
    public XPComposterBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int val = 0;
        if (stack.isIn(ModTags.Items.XP_COMPOSTABLE_SMALL)) val = 1;
        if (stack.isIn(ModTags.Items.XP_COMPOSTABLE_MEDIUM)) val = 2;
        if (stack.isIn(ModTags.Items.XP_COMPOSTABLE_LARGE)) val = 3;
        if (val > 0) {
            if (!world.isClient) {
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                stack.decrementUnlessCreative(1, player);
                Vec3d vec3d = Vec3d.add(pos, 0.5, 1.01, 0.5).addRandom(world.random, 0.7F);
                ExperienceOrbEntity xpEntity = new ExperienceOrbEntity(world, vec3d.getX(), vec3d.getY(), vec3d.getZ(), val);
                world.spawnEntity(xpEntity);
            }
            return ItemActionResult.success(world.isClient);
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}

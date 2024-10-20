package io.github.trashoflevillage.mushroommadness.mixin;

import io.github.trashoflevillage.mushroommadness.blocks.ModBlocks;
import io.github.trashoflevillage.mushroommadness.blocks.custom.MycologistTowerGeneratorBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldMixin {
//    @Shadow public abstract boolean removeBlock(BlockPos pos, boolean move);
//
//    @Shadow public abstract WorldChunk getWorldChunk(BlockPos pos);
//
//    @Inject(method = "setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;II)Z", at = @At("TAIL"))
//    protected void setBlockState(BlockPos pos, BlockState state, int flags, int maxUpdateDepth, CallbackInfoReturnable<Boolean> cir) {
//        if (state.getBlock() == ModBlocks.MYCOLOGIST_TOWER_GENERATOR) {
//            removeBlock(pos, false);
//            MycologistTowerGeneratorBlock.generateStructure(this.getWorldChunk(pos).getWorld(), pos);
//        }
//    }
}

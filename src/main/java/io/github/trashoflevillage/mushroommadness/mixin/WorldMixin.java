package io.github.trashoflevillage.mushroommadness.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

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

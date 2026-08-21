package dtteam.dtru.mixins;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.regions_unexplored.block.type.dirt.AshenDirtBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AshenDirtBlock.class)
public abstract class AshenDirtBlockSafeMixin extends Block {
    @Final
    @Shadow public static BooleanProperty SMOULDERING;

    public AshenDirtBlockSafeMixin(Properties properties){
        super(properties);
    }

    @Inject(method = "isSmouldering", at = @At("HEAD"), cancellable = true)
    private static void safeIsSmouldering(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (SMOULDERING == null || !state.hasProperty(SMOULDERING)) {
            cir.setReturnValue(false);
        }
    }

}


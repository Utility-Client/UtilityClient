package org.utilityclient.mixin;

import net.minecraft.world.dimension.Dimension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.utilityclient.config.Config;

@Mixin(Dimension.class)
public class CustomCloudHeight {
    @Inject(at = @At("RETURN"), method = "getCloudHeight()F", cancellable = true)
    private void getCloudHeight(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(Config.getFloat("world.cloudHeight", 256));
    }
}

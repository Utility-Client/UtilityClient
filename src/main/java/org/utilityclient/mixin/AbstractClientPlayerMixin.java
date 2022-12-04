package org.utilityclient.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.utilityclient.UtilityClient;

@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerMixin {
    @Inject(at = @At("RETURN"), method = "getSpeed()F", cancellable = true)
    private void getSpeed(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(cir.getReturnValue() * UtilityClient.fovModifier);
    }
}

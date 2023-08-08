package org.utilityclient.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.impl.WrapperImpl;

@Mixin(MinecraftClient.class)
public class ClientMixins {
	@Inject(at = @At("TAIL"), method = "initializeGame()V")
	private void init(CallbackInfo ci) {
		new WrapperImpl();
		UtilityClient.getInstance().start();
	}

	@Inject(at = @At("TAIL"), method = "tick()V")
	private void tick(CallbackInfo ci) {
		UtilityClient.getInstance().loop();
	}

	@Inject(at = @At("HEAD"), method = "scheduleStop()V")
	private void scheduleStop(CallbackInfo ci) {
		try {
			Config.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
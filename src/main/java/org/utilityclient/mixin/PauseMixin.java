package org.utilityclient.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.utilityclient.gui.overrides.GuiIngameMenu;

@Mixin(GameMenuScreen.class)
public class PauseMixin {
    @Inject(at = @At("HEAD"), method = "init()V", cancellable = true)
    public void init(CallbackInfo ci) {
        MinecraftClient.getInstance().openScreen(new GuiIngameMenu());
        ci.cancel();
    }
}

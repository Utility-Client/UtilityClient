package org.utilityclient.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.utilityclient.gui.overrides.GuiMultiplayer;

@Mixin(MultiplayerScreen.class)
public class MultiplayerGuiMixin {
    @Inject(at = @At("HEAD"), method = "init()V", cancellable = true)
    public void init(CallbackInfo ci) {
        MultiplayerScreen mps = (MultiplayerScreen)MinecraftClient.getInstance().currentScreen;
        MinecraftClient.getInstance().openScreen(new GuiMultiplayer(((GuiMultiplayerAccessor)mps).getParent()));
        ci.cancel();
    }
}

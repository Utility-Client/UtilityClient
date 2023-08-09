package org.utilityclient.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.utilityclient.api.abstraction.Uncommon;
import org.utilityclient.gui.overrides.GuiMainMenu;

@Mixin(TitleScreen.class) @Uncommon
public class TitleScreenMixin {
    @Inject(at = @At("HEAD"), method = "init()V", cancellable = true)
    public void init(CallbackInfo ci) {
        MinecraftClient.getInstance().openScreen(new GuiMainMenu());
        ci.cancel();
    }
}

package org.utilityclient.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SettingsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.utilityclient.gui.overrides.GuiOptions;

@Mixin(SettingsScreen.class)
public class OptionsMixin {

    @Inject(at = @At("HEAD"), method = "init()V", cancellable = true)
    private void init(CallbackInfo ci) {
        if (MinecraftClient.getInstance().currentScreen instanceof SettingsScreen ss) {
            MinecraftClient.getInstance().openScreen(new GuiOptions(((OptionsAccessor)ss).getParent(), MinecraftClient.getInstance().options));
        }


        ci.cancel();
    }
}

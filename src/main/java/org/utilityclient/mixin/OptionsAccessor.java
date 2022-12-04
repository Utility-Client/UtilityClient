package org.utilityclient.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SettingsScreen.class)
public interface OptionsAccessor {
    @Accessor()
    Screen getParent();
}

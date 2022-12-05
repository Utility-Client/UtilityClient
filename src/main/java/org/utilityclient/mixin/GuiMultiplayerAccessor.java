package org.utilityclient.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MultiplayerScreen.class)
public interface GuiMultiplayerAccessor {
    @Accessor
    Screen getParent();
}

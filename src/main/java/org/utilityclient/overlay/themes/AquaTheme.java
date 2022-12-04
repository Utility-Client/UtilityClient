package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.overlay.ITheme;

public class AquaTheme implements ITheme {
    @Override
    public String getName() {
        return "Aqua Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_AQUA;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.AQUA;
    }
}

package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.overlay.ITheme;

public class PurpleTheme implements ITheme {
    @Override
    public String getName() {
        return "Purple Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_PURPLE;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.LIGHT_PURPLE;
    }
}

package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Theme;

public class PurpleTheme implements Theme {
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

package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Theme;

public class RedTheme implements Theme {
    @Override
    public String getName() {
        return "Red Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_RED;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.RED;
    }
}

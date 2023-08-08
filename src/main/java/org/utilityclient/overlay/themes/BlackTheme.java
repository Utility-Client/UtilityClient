package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Theme;

public class BlackTheme implements Theme {
    @Override
    public String getName() {
        return "Black Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.BLACK;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.DARK_GRAY;
    }
}

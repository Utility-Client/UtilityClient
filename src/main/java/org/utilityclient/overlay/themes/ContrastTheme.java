package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Theme;

public class ContrastTheme implements Theme {
    @Override
    public String getName() {
        return "Contrast Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.BLACK;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.WHITE;
    }
}

package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.overlay.ITheme;

public class RedTheme implements ITheme {
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
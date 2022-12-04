package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.overlay.ITheme;

public class GrayTheme implements ITheme {
    @Override
    public String getName() {
        return "Gray Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_GRAY;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.GRAY;
    }
}

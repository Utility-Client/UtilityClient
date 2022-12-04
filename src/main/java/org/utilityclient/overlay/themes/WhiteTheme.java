package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.overlay.ITheme;

public class WhiteTheme implements ITheme {
    @Override
    public String getName() {
        return "White Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.WHITE;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return getPrefixColor();
    }
}

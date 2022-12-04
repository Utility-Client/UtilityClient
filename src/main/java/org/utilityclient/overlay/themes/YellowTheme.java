package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.overlay.ITheme;

public class YellowTheme implements ITheme {
    @Override
    public String getName() {
        return "Yellow Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.GOLD;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.YELLOW;
    }
}

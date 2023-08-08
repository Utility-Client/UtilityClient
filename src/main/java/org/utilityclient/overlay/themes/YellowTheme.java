package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Theme;

public class YellowTheme implements Theme {
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

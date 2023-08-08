package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Theme;

public class BlueTheme implements Theme {
    @Override
    public String getName() {
        return "Blue Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_BLUE;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.BLUE;
    }
}

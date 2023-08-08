package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Theme;

public class AquaTheme implements Theme {
    @Override
    public String getName() {
        return "Aqua Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_AQUA;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.AQUA;
    }
}

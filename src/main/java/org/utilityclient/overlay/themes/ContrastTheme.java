package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register
public class ContrastTheme extends Theme {
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

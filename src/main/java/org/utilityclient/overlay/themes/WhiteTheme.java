package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register
public class WhiteTheme extends Theme {
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

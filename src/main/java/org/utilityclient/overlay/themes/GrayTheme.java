package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register
public class GrayTheme extends Theme {
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

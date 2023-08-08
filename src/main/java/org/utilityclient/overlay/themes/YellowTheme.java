package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register
public class YellowTheme extends Theme {
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

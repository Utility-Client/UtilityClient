package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register
public class GreenTheme extends Theme {
    @Override
    public String getName() {
        return "Green Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_GREEN;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.GREEN;
    }
}

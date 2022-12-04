package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.overlay.ITheme;

public class GreenTheme implements ITheme {
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

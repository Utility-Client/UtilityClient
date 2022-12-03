package org.utilityclient.overlay.themes;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.ITheme;

public class WhiteTheme implements ITheme {
    @Override
    public String getName() {
        return "White Theme";
    }

    @Override
    public EnumChatFormatting getPrefixColor() {
        return EnumChatFormatting.WHITE;
    }

    @Override
    public EnumChatFormatting getSuffixColor() {
        return getPrefixColor();
    }
}

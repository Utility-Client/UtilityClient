package org.utilityclient.overlay.themes;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.ITheme;

public class BlackTheme implements ITheme {
    @Override
    public String getName() {
        return "Black Theme";
    }

    @Override
    public EnumChatFormatting getPrefixColor() {
        return EnumChatFormatting.BLACK;
    }

    @Override
    public EnumChatFormatting getSuffixColor() {
        return EnumChatFormatting.DARK_GRAY;
    }
}

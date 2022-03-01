package org.utilityclient.overlay.themes;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.ITheme;

public class RedTheme implements ITheme {
    @Override
    public String getName() {
        return "Red Theme";
    }

    @Override
    public EnumChatFormatting getPrefixColor() {
        return EnumChatFormatting.DARK_RED;
    }

    @Override
    public EnumChatFormatting getSuffixColor() {
        return EnumChatFormatting.RED;
    }
}

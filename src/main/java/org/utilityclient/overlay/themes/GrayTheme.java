package org.utilityclient.overlay.themes;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.ITheme;

public class GrayTheme implements ITheme {
    @Override
    public String getName() {
        return "Gray Theme";
    }

    @Override
    public EnumChatFormatting getPrefixColor() {
        return EnumChatFormatting.DARK_GRAY;
    }

    @Override
    public EnumChatFormatting getSuffixColor() {
        return EnumChatFormatting.GRAY;
    }
}

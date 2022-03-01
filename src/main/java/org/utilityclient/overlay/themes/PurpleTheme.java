package org.utilityclient.overlay.themes;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.ITheme;

public class PurpleTheme implements ITheme {
    @Override
    public String getName() {
        return "Purple Theme";
    }

    @Override
    public EnumChatFormatting getPrefixColor() {
        return EnumChatFormatting.DARK_PURPLE;
    }

    @Override
    public EnumChatFormatting getSuffixColor() {
        return EnumChatFormatting.LIGHT_PURPLE;
    }
}

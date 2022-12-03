package org.utilityclient.overlay.themes;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.ITheme;

public class YellowTheme implements ITheme {
    @Override
    public String getName() {
        return "Yellow Theme";
    }

    @Override
    public EnumChatFormatting getPrefixColor() {
        return EnumChatFormatting.GOLD;
    }

    @Override
    public EnumChatFormatting getSuffixColor() {
        return EnumChatFormatting.YELLOW;
    }
}

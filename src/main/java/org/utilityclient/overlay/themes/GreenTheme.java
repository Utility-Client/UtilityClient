package org.utilityclient.overlay.themes;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.ITheme;

public class GreenTheme implements ITheme {
    @Override
    public String getName() {
        return "Green Theme";
    }

    @Override
    public EnumChatFormatting getPrefixColor() {
        return EnumChatFormatting.DARK_GREEN;
    }

    @Override
    public EnumChatFormatting getSuffixColor() {
        return EnumChatFormatting.GREEN;
    }
}

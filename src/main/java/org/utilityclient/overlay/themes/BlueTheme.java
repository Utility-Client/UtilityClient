package org.utilityclient.overlay.themes;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.ITheme;

public class BlueTheme implements ITheme {
    @Override
    public String getName() {
        return "Blue Theme";
    }

    @Override
    public EnumChatFormatting getPrefixColor() {
        return EnumChatFormatting.DARK_BLUE;
    }

    @Override
    public EnumChatFormatting getSuffixColor() {
        return EnumChatFormatting.BLUE;
    }
}

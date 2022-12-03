package org.utilityclient.overlay.themes;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.ITheme;

public class AquaTheme implements ITheme {
    @Override
    public String getName() {
        return "Aqua Theme";
    }

    @Override
    public EnumChatFormatting getPrefixColor() {
        return EnumChatFormatting.DARK_AQUA;
    }

    @Override
    public EnumChatFormatting getSuffixColor() {
        return EnumChatFormatting.AQUA;
    }
}

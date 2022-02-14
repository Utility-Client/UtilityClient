package org.utilityclient.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;

public interface ITheme {
    String getName();
    EnumChatFormatting getPrefixColor();
    EnumChatFormatting getSuffixColor();
    default String getSeparator() {
        return EnumChatFormatting.GRAY + ": ";
    }
    default FontRenderer getFontRenderer() {
        return Minecraft.getMinecraft().fontRendererObj;
    }
}

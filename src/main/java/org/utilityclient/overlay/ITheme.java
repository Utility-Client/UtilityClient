package org.utilityclient.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;

/**
 * @since 2.15 LTS
 * @author GamingCraft
 */
public interface ITheme {
    /**
     * @since 2.15 LTS
     * @return The name of the theme
     */
    String getName();

    /**
     * @return The prefix color
     */
    EnumChatFormatting getPrefixColor();

    /**
     * @return The suffix color
     */
    EnumChatFormatting getSuffixColor();

    /**
     * @since 2.15 LTS
     * @return The separator between prefix and suffix.
     */
    default String getSeparator() {
        return EnumChatFormatting.GRAY + ": ";
    }

    /**
     * @since 2.15 LTS
     * @return The used font renderer. Override this to render using other font renderers.
     */
    default FontRenderer getFontRenderer() {
        return Minecraft.getMinecraft().fontRendererObj;
    }
}

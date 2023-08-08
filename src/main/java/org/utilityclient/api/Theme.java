package org.utilityclient.api;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;

/**
 * @apiNote Previously known as "ITheme", refactored in 3.0 LTS
 * @author Sam302
 * @since 2.15 LTS
 */
public interface Theme {
    /**
     * @since 2.15 LTS
     * @return The name of the theme
     */
    String getName();

    /**
     * @return The prefix color
     */
    ChatFormatting getPrefixColor();

    /**
     * @return The suffix color
     */
    ChatFormatting getSuffixColor();

    /**
     * @since 2.15 LTS
     * @return The separator between prefix and suffix.
     */
    default String getSeparator() {
        return ChatFormatting.GRAY + ": ";
    }

    /**
     * @since 2.15 LTS
     * @return The used font renderer. Override this to render using other font renderers.
     */
    default TextRenderer getFontRenderer() {
        return MinecraftClient.getInstance().textRenderer;
    }
}

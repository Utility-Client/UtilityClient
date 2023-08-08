package org.utilityclient.api;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import org.utilityclient.UtilityClient;

/**
 * @apiNote Previously known as "ITheme", refactored in 3.0 LTS
 * @author Sam302
 * @since 2.15 LTS
 */
public abstract class Theme extends Registrable {
    /**
     * @since 2.15 LTS
     * @return The name of the theme
     */
    public abstract String getName();

    /**
     * @return The prefix color
     */
    public abstract ChatFormatting getPrefixColor();

    /**
     * @return The suffix color
     */
    public abstract ChatFormatting getSuffixColor();

    /**
     * @since 2.15 LTS
     * @return The separator between prefix and suffix.
     */
    public String getSeparator() {
        return ChatFormatting.GRAY + ": ";
    }

    /**
     * @since 2.15 LTS
     * @return The used font renderer. Override this to render using other font renderers.
     */
    public TextRenderer getFontRenderer() {
        return MinecraftClient.getInstance().textRenderer;
    }

    @Override
    public void register() {
        UtilityClient.themes.add(this);
    }
}

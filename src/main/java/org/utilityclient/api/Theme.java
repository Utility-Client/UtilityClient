package org.utilityclient.api;

import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;
import org.utilityclient.UtilityClient;

/**
 * @apiNote Previously known as "ITheme", refactored in 3.0
 * @apiNote Removed getFontRenderer() in 3.0
 * @author Sam302
 * @since 2.15 LTS
 */
@StandaloneCompatible
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

    @Override
    public void register() {
        UtilityClient.themes.add(this);
    }
}

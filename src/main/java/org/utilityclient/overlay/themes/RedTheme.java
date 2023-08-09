package org.utilityclient.overlay.themes;

import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register @StandaloneCompatible
public class RedTheme extends Theme {
    @Override
    public String getName() {
        return "Red Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_RED;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.RED;
    }
}

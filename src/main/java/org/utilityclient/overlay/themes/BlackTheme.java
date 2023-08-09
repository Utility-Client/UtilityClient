package org.utilityclient.overlay.themes;

import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register @StandaloneCompatible
public class BlackTheme extends Theme {
    @Override
    public String getName() {
        return "Black Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.BLACK;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.DARK_GRAY;
    }
}

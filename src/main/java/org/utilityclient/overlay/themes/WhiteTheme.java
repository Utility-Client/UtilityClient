package org.utilityclient.overlay.themes;

import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register @StandaloneCompatible
public class WhiteTheme extends Theme {
    @Override
    public String getName() {
        return "White Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.WHITE;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return getPrefixColor();
    }
}

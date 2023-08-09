package org.utilityclient.overlay.themes;

import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;
import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;

@Register @StandaloneCompatible
public class YellowTheme extends Theme {
    @Override
    public String getName() {
        return "Yellow Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.GOLD;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.YELLOW;
    }
}

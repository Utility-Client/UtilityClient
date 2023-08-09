package org.utilityclient.overlay.themes;

import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register @StandaloneCompatible
public class BlueTheme extends Theme {
    @Override
    public String getName() {
        return "Blue Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_BLUE;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.BLUE;
    }
}

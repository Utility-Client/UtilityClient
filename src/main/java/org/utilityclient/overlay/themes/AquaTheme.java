package org.utilityclient.overlay.themes;

import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register @StandaloneCompatible
public class AquaTheme extends Theme {
    @Override
    public String getName() {
        return "Aqua Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_AQUA;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.AQUA;
    }
}

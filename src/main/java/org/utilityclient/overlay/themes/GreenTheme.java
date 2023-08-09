package org.utilityclient.overlay.themes;

import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;

@Register @StandaloneCompatible
public class GreenTheme extends Theme {
    @Override
    public String getName() {
        return "Green Theme";
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return ChatFormatting.DARK_GREEN;
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return ChatFormatting.GREEN;
    }
}

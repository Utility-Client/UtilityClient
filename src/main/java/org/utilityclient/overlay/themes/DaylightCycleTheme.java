package org.utilityclient.overlay.themes;

import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;
import org.utilityclient.api.Register;
import org.utilityclient.api.Theme;
import org.utilityclient.utils.Utils;

@Register @StandaloneCompatible
public class DaylightCycleTheme extends Theme {
    @Override
    public String getName() {
        return "Daylight Cycle Theme";
    }

    private ChatFormatting getColorByTime(ChatFormatting day, ChatFormatting night) {
        long secs = Utils.getSecondsOfDay();
        if(secs >= 21600 && secs <= 64800) return day;
        return night;
    }

    @Override
    public ChatFormatting getPrefixColor() {
        return getColorByTime(ChatFormatting.DARK_RED, ChatFormatting.DARK_BLUE);
    }

    @Override
    public ChatFormatting getSuffixColor() {
        return getColorByTime(ChatFormatting.RED, ChatFormatting.BLUE);
    }
}

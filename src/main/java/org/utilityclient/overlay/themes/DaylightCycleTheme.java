package org.utilityclient.overlay.themes;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.utilityclient.overlay.ITheme;
import org.utilityclient.utils.Utils;

public class DaylightCycleTheme implements ITheme {
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

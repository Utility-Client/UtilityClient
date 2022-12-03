package org.utilityclient.overlay.themes;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.ITheme;
import org.utilityclient.utils.Utils;

public class DaylightCycleTheme implements ITheme {
    @Override
    public String getName() {
        return "Daylight Cycle Theme";
    }

    private EnumChatFormatting getColorByTime(EnumChatFormatting day, EnumChatFormatting night) {
        long secs = Utils.getSecondsOfDay();
        if(secs >= 21600 && secs <= 64800) return day;
        return night;
    }

    @Override
    public EnumChatFormatting getPrefixColor() {
        return getColorByTime(EnumChatFormatting.DARK_RED, EnumChatFormatting.DARK_BLUE);
    }

    @Override
    public EnumChatFormatting getSuffixColor() {
        return getColorByTime(EnumChatFormatting.RED, EnumChatFormatting.BLUE);
    }
}

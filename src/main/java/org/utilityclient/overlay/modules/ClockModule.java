package org.utilityclient.overlay.modules;

import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.api.Module;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;

public class ClockModule extends Module {
    @Override
    public String getName() {
        return "Clock";
    }

    @Override
    public String getValue() {
        return LocalDateTime.now().format(new DateTimeFormatterBuilder().appendPattern(Config.getString(ConfigEntry.TIME_PATTERN)).toFormatter());
    }

    @Override
    public String getAuthor() {
        return "Sam302";
    }
}

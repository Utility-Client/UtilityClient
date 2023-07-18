package org.utilityclient.overlay.modules;

import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.overlay.IModule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.temporal.ChronoField.*;

public class ClockModule extends IModule {
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

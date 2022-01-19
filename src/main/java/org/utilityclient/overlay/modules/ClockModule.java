package org.utilityclient.overlay.modules;

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
        return LocalDateTime.now().format(new DateTimeFormatterBuilder()
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .toFormatter());
    }

    @Override
    public String getAuthor() {
        return "GamingCraft_hd";
    }
}

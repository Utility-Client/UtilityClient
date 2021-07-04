package de.gamingcraft.overlay.modules;

import de.gamingcraft.overlay.IModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.util.Date;

import static java.time.temporal.ChronoField.*;

public class ClockModule implements IModule {
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

package org.utilityclient.overlay.modules;

import org.utilityclient.overlay.IModule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;

import static java.time.temporal.ChronoField.*;

public class DateModule extends IModule {
    @Override
    public String getName() {
        return "Date";
    }

    @Override
    public String getValue() {
        return LocalDate.now().format(new DateTimeFormatterBuilder()
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral('/')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('/')
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .toFormatter());
    }

    @Override
    public String getAuthor() {
        return "GamingCraft_hd";
    }
}

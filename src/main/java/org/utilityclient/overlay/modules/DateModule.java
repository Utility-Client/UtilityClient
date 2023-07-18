package org.utilityclient.overlay.modules;

import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
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
        return LocalDate.now().format(new DateTimeFormatterBuilder().appendPattern(Config.getString(ConfigEntry.DATE_PATTERN)).toFormatter());
    }

    @Override
    public String getAuthor() {
        return "Sam302";
    }
}

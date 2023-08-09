package org.utilityclient.overlay.modules;

import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.api.Module;

import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;

@Register @StandaloneCompatible
public class DateModule extends Module {
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

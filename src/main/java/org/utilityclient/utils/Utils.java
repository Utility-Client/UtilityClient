package org.utilityclient.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.utilityclient.config.Config;

import java.time.Duration;
import java.time.Instant;
import java.time.Month;
import java.time.ZonedDateTime;

/**
 * General Utils, that don't need a whole class.
 * @since 2.14
 * @author GamingCraft
 */
public class Utils {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Logs the return value of a function call, that might not be useful.
     * @param object The return value, that isn't useful.
     * @implNote Could be extended & overridden to do something else with the Object.
     */
    public static void ignore(Object object) {
        logger.debug(object);
    }

    /**
     * Logs object, if shouldLog is true. See {@link #ignore(Object)} for more details.
     * @param object The return value, that isn't useful.
     * @param shouldLog Should be the object logged or just fully ignored?
     */
    public static void ignore(Object object, boolean shouldLog) {
        if(shouldLog) logger.debug(object);
    }

    /**
     * Get seconds passed since midnight.
     * @return Seconds passed since midnight as Long.
     * @see ZonedDateTime
     * @see Duration
     * @see Instant
     */
    public static long getSecondsOfDay() {
        ZonedDateTime nowZoned = ZonedDateTime.now();
        return Duration.between(nowZoned.toLocalDate().atStartOfDay(nowZoned.getZone()).toInstant(), Instant.now()).getSeconds();
    }

    public static Season getSeasonOfMonth(int month) {
        if (Config.getBoolean("disableSeasonalTitleScreen", false)) return Season.NONE;

        switch (month) {
            case 1:
            case 2:
            case 11:
            case 12:
                return Season.WINTER;

            case 3:
            case 4:
            case 5:
                return Season.SPRING;

            case 6:
            case 7:
            case 8:
                return Season.SUMMER;

            case 9:
            case 10:
                return Season.FALL;
        }

        return Season.NONE;
    }
}

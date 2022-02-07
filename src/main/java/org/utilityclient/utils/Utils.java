package org.utilityclient.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Logs the return value of a function call, that might not be useful.
     * @param object The return value, that isn't useful.
     */
    public static void ignore(Object object) {
        logger.debug(object);
    }
}

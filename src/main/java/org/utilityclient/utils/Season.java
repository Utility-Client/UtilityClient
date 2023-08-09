package org.utilityclient.utils;

import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.config.Config;

@StandaloneCompatible
public enum Season {
    WINTER("textures/utilityclient/backgrounds/winter.png"),
    SPRING("textures/utilityclient/backgrounds/spring.png"),
    SUMMER("textures/utilityclient/backgrounds/summer.png"),
    FALL("textures/utilityclient/backgrounds/fall.png"),
    NONE("textures/utilityclient/backgrounds/default.png");

    final String id;

    Season(String i) {
        id = i;
    }

    public String getIdentifier() {
        return id;
    }

    public static Season getSeasonOfMonth(int month) {
        if (Config.getBoolean("title.seasons.disable", false)) return Season.NONE;

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

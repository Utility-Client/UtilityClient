package org.utilityclient.utils;

import net.minecraft.util.Identifier;
import org.utilityclient.config.Config;

public enum Season {
    WINTER(new Identifier("textures/utilityclient/backgrounds/winter.png")),
    SPRING(new Identifier("textures/utilityclient/backgrounds/spring.png")),
    SUMMER(new Identifier("textures/utilityclient/backgrounds/summer.png")),
    FALL(new Identifier("textures/utilityclient/backgrounds/fall.png")),
    NONE(new Identifier("textures/utilityclient/backgrounds/default.png"));

    final Identifier id;

    Season(Identifier i) {
        id = i;
    }

    public Identifier getIdentifier() {
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

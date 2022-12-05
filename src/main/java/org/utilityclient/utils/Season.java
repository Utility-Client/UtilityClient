package org.utilityclient.utils;

import net.minecraft.util.Identifier;

public enum Season {
    WINTER(new Identifier("utilityclient", "backgrounds/winter.png")),
    SPRING(new Identifier("utilityclient", "backgrounds/spring.png")),
    SUMMER(new Identifier("utilityclient", "backgrounds/summer.png")),
    FALL(new Identifier("utilityclient", "backgrounds/fall.png")),
    NONE(new Identifier("utilityclient", "backgrounds/default.png"));

    final Identifier id;

    Season(Identifier i) {
        id = i;
    }

    public Identifier getIdentifier() {
        return id;
    }
}

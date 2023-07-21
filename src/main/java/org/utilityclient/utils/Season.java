package org.utilityclient.utils;

import net.minecraft.util.Identifier;

public enum Season {
    WINTER(new Identifier("utilityclient", "textures/backgrounds/winter.png")),
    SPRING(new Identifier("utilityclient", "textures/backgrounds/spring.png")),
    SUMMER(new Identifier("utilityclient", "textures/backgrounds/summer.png")),
    FALL(new Identifier("utilityclient", "textures/backgrounds/fall.png")),
    NONE(new Identifier("utilityclient", "textures/backgrounds/default.png"));

    final Identifier id;

    Season(Identifier i) {
        id = i;
    }

    public Identifier getIdentifier() {
        return id;
    }
}

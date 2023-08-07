package org.utilityclient.utils;

import net.minecraft.util.Identifier;

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
}

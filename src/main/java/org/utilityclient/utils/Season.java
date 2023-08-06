package org.utilityclient.utils;

import net.minecraft.util.Identifier;

public enum Season {
    WINTER(new Identifier("textures/backgrounds/winter.png")),
    SPRING(new Identifier("textures/backgrounds/spring.png")),
    SUMMER(new Identifier("textures/backgrounds/summer.png")),
    FALL(new Identifier("textures/backgrounds/fall.png")),
    NONE(new Identifier("textures/backgrounds/default.png"));

    final Identifier id;

    Season(Identifier i) {
        id = i;
    }

    public Identifier getIdentifier() {
        return id;
    }
}

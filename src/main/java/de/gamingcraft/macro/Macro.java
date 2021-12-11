package de.gamingcraft.macro;

import net.minecraft.client.settings.KeyBinding;

public class Macro {
    public final String name, message;
    public final int keyCode;
    public KeyBinding keyBinding;

    public Macro(String name, String message, int keyCode) {
        this.name = name;
        this.message = message;
        this.keyCode = keyCode;
    }

    public Macro(String name, String message, int keyCode, KeyBinding keyBinding) {
        this(name, message, keyCode);
        this.keyBinding = keyBinding;
    }
}

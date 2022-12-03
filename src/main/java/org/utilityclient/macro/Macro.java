package org.utilityclient.macro;

import net.minecraft.client.settings.KeyBinding;

import java.io.File;

public class Macro {
    public final String name, message;
    public final int keyCode;
    public File file;
    public KeyBinding keyBinding;

    public Macro(String name, String message, int keyCode) {
        this.name = name;
        this.message = message;
        this.keyCode = keyCode;
    }

    public Macro(String name, String message, int keyCode, KeyBinding keyBinding, File file) {
        this(name, message, keyCode);
        this.keyBinding = keyBinding;
        this.file = file;
    }
}

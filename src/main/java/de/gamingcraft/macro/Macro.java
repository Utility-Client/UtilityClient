package de.gamingcraft.macro;

import net.minecraft.client.settings.KeyBinding;

public class Macro {

    private String name, command;
    private int keyCode;
    private KeyBinding kb;

    public Macro(String _name, String _command, int _keyCode) {
        this.name = _name;
        this.command = _command;
        this.keyCode = _keyCode;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyBinding(KeyBinding _kb) {
        this.kb = _kb;
    }

    public KeyBinding getKeyBinding() {
        return this.kb;
    }
}

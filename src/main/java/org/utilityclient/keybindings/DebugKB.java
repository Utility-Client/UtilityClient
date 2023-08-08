package org.utilityclient.keybindings;

import org.lwjgl.input.Keyboard;
import org.utilityclient.api.KeyBinding;
import org.utilityclient.api.Register;

@Register
public class DebugKB extends KeyBinding {
    public DebugKB() {
        super("Debug", "Debug", Keyboard.KEY_C, true, false, false);
    }

    @Override
    public void hold() {
        System.out.println("hold");
    }

    @Override
    public void down() {
        System.out.println("down");
    }

    @Override
    public void up() {
        System.out.println("up");
    }

    @Override
    public void register() {
        super.register();
        System.out.println("register");
    }
}

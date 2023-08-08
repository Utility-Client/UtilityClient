package org.utilityclient.api;

import org.lwjgl.input.Keyboard;
import org.utilityclient.UtilityClient;

/**
 * A custom & advanced KeyBinding implementation for UtilityClient.
 * @see net.minecraft.client.options.KeyBinding
 * @author Sam302
 * @since 3.0
 */
public abstract class KeyBinding extends Registrable {
    public final String Category, Name;
    public final int KeyCode;
    public final boolean Ctrl, Shift, Alt;
    protected boolean lastFrameDown;

    public KeyBinding(String category, String name, int keyCode, boolean ctrl, boolean shift, boolean alt) {
        this.Category = category;
        this.Name = name;
        this.KeyCode = keyCode;
        this.Ctrl = ctrl;
        this.Shift = shift;
        this.Alt = alt;
    }

    public void frame() {
        boolean ctrl = !Ctrl || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
        boolean shift = !Shift || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
        boolean alt = !Alt || Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU);
        boolean key = Keyboard.isKeyDown(KeyCode);

        if (ctrl && shift && alt && key) {
            if (!lastFrameDown) down();
            lastFrameDown = true;
            hold();
        } else {
            if (lastFrameDown) up();
            lastFrameDown = false;
        }
    }

    /**
     * The key is continuously hold.
     */
    public void hold() {}

    /**
     * Only gets once fired at the start of every press.
     */
    public void down() {}

    /**
     * Only gets once fired after every press.
     */
    public void up() {}

    @Override
    public void register() {
        UtilityClient.keyBinds.add(this);
    }
}

package org.utilityclient.api;

import org.utilityclient.UtilityClient;
import org.utilityclient.api.abstraction.StandaloneCompatible;

/**
 * A custom & advanced KeyBinding implementation for UtilityClient.
 * @see net.minecraft.client.options.KeyBinding
 * @author Sam302
 * @since 3.0
 */
@StandaloneCompatible
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
        boolean ctrl = !Ctrl || uc().wrapper.isKeyDown(0x1D) || uc().wrapper.isKeyDown(0x9D);
        boolean shift = !Shift || uc().wrapper.isKeyDown(0x2A) || uc().wrapper.isKeyDown(0x36);
        boolean alt = !Alt || uc().wrapper.isKeyDown(0x38) || uc().wrapper.isKeyDown(0xB8);
        boolean key = uc().wrapper.isKeyDown(KeyCode);

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

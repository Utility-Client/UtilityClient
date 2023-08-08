package org.utilityclient.input;

/**
 * A custom & advanced KeyBinding implementation for UtilityClient.
 * @see net.minecraft.client.options.KeyBinding
 * @author Sam302
 * @since 3.0
 */
public abstract class KeyBinding {
    public final String Category, Name;
    public final int KeyCode, Modifiers;

    public KeyBinding(String category, String name, int keyCode, int modifiers) {
        this.Category = category;
        this.Name = name;
        this.KeyCode = keyCode;
        this.Modifiers = modifiers;
    }

    /**
     * The key is continuously hold.
     */
    public abstract void hold();

    /**
     * Only gets once fired after every press.
     */
    public abstract void up();
}

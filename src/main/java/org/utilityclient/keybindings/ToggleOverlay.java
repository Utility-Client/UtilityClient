package org.utilityclient.keybindings;

import org.lwjgl.input.Keyboard;
import org.utilityclient.UtilityClient;
import org.utilityclient.api.KeyBinding;
import org.utilityclient.api.Register;

@Register
public class ToggleOverlay extends KeyBinding {
    public ToggleOverlay() {
        super("Overlay", "Toggle Overlay Visibility", Keyboard.KEY_F10, false, false, true);
    }

    @Override
    public void up() {
        UtilityClient.renderOverlay = !UtilityClient.renderOverlay;
    }
}

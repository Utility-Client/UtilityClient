package org.utilityclient.keybindings;

import org.utilityclient.UtilityClient;
import org.utilityclient.api.KeyBinding;
import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;

@Register @StandaloneCompatible
public class ToggleOverlay extends KeyBinding {
    public ToggleOverlay() {
        super("Overlay", "Toggle Overlay Visibility", 0x44, false, false, true);
    }

    @Override
    public void up() {
        UtilityClient.renderOverlay = !UtilityClient.renderOverlay;
    }
}

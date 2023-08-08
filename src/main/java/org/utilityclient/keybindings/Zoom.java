package org.utilityclient.keybindings;

import org.utilityclient.UtilityClient;
import org.utilityclient.api.KeyBinding;
import org.utilityclient.api.Register;
import org.utilityclient.config.*;

@Register
public class Zoom extends KeyBinding {
    public Zoom() {
        super("General", "Zoom", 46, false, false, false);
    }

    @Override
    public void down() {
        UtilityClient.fovModifier = Config.getFloat(ConfigEntry.ZOOM_FACTOR);
    }

    @Override
    public void up() {
        UtilityClient.fovModifier = 1.0f;
    }
}

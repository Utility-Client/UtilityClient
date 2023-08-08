package org.utilityclient.keybindings;

import org.utilityclient.api.KeyBinding;
import org.utilityclient.api.Register;

@Register
public class Fulbright extends KeyBinding {
    public Fulbright() {
        super("General", "Toggle Fulbright", 50, false, false, false);
    }

    @Override
    public void up() {
        if (mc().options.gamma == 1.0f) {
            mc().options.gamma = 999999;
            uc().isFulbrightEnabled = true;
        } else {
            mc().options.gamma = 1.0f;
            uc().isFulbrightEnabled = false;
        }
    }
}

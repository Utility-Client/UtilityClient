package org.utilityclient.keybindings;

import org.utilityclient.api.KeyBinding;
import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;

@Register @StandaloneCompatible
public class Fulbright extends KeyBinding {
    public Fulbright() {
        super("General", "Toggle Fulbright", 50, false, false, false);
    }

    @Override
    public void up() {
        if (uc().wrapper.getGamma() == 1.0f) {
            uc().wrapper.setGamma(999999);
            uc().isFulbrightEnabled = true;
        } else {
            uc().wrapper.setGamma(1);
            uc().isFulbrightEnabled = false;
        }
    }
}

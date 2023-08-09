package org.utilityclient.overlay.modules;

import org.utilityclient.api.Module;
import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;

@Register @StandaloneCompatible
public class BiomeModule extends Module {

    @Override
    public String getName() {
        return "Biome";
    }

    @Override
    public String getValue() {
        return uc().wrapper.getPlayerBiome();
    }

    @Override
    public String getAuthor() {
        return "Sam302";
    }
}

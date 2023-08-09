package org.utilityclient.overlay.modules;

import org.utilityclient.UtilityClient;
import org.utilityclient.api.Module;
import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;

@Register @StandaloneCompatible
public class CoordsModule extends Module {
    @Override
    public String getName() {
        return "Coords";
    }

    @Override
    public String getValue() {
        if(UtilityClient.streamerMode) return "<disabled>";
        return uc().wrapper.getPlayerPosition(", ");
    }

    @Override
    public String getAuthor() {
        return "Sam302";
    }
}

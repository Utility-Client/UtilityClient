package org.utilityclient.overlay.modules;

import org.utilityclient.api.Module;
import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;

@Register @StandaloneCompatible
public class FacingModule extends Module {
    @Override
    public String getName() {
        return "Facing";
    }

    @Override
    public String getValue() {
        char[] ca = uc().wrapper.getPlayerFacing();
        ca[0] = (ca[0]+"").toUpperCase().toCharArray()[0];
        return new String(ca);
    }

    @Override
    public String getAuthor() {
        return "marioboss56";
    }
}

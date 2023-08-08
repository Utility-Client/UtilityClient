package org.utilityclient.overlay.modules;

import org.utilityclient.api.Module;

public class FacingModule extends Module {
    @Override
    public String getName() {
        return "Facing";
    }

    @Override
    public String getValue() {
        char[] ca = mc().getCameraEntity().getHorizontalDirection().getName().toCharArray();
        ca[0] = (ca[0]+"").toUpperCase().toCharArray()[0];
        return new String(ca);
    }

    @Override
    public String getAuthor() {
        return "marioboss56";
    }
}

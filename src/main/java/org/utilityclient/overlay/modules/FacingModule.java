package org.utilityclient.overlay.modules;

import org.utilityclient.overlay.IModule;

public class FacingModule extends IModule {
    @Override
    public String getName() {
        return "Facing";
    }

    @Override
    public String getValue() {
        String str = mc().targetedEntity.getCustomName();;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @Override
    public String getAuthor() {
        return "marioboss56";
    }
}

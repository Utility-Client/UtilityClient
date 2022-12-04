package org.utilityclient.overlay.modules;

import org.utilityclient.UtilityClient;
import org.utilityclient.overlay.IModule;

public class CoordsModule extends IModule {
    @Override
    public String getName() {
        return "Coords";
    }

    @Override
    public String getValue() {
        if(UtilityClient.streamerMode) return "<disabled>";

        return    mc().player.getPos().x + ", "
                + mc().player.getPos().y + ", "
                + mc().player.getPos().z;
    }

    @Override
    public String getAuthor() {
        return "GamingCraft";
    }
}

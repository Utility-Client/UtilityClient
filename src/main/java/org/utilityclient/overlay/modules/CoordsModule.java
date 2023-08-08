package org.utilityclient.overlay.modules;

import org.utilityclient.UtilityClient;
import org.utilityclient.api.Module;

public class CoordsModule extends Module {
    @Override
    public String getName() {
        return "Coords";
    }

    @Override
    public String getValue() {
        if(UtilityClient.streamerMode) return "<disabled>";

        return    mc().player.getBlockPos().getX() + ", "
                + mc().player.getBlockPos().getY() + ", "
                + mc().player.getBlockPos().getZ();
    }

    @Override
    public String getAuthor() {
        return "Sam302";
    }
}

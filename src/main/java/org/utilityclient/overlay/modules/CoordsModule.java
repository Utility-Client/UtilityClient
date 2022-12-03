package org.utilityclient.overlay.modules;

import org.utilityclient.UtilityClient;
import org.utilityclient.overlay.IModule;
import net.minecraft.client.Minecraft;

public class CoordsModule extends IModule {
    @Override
    public String getName() {
        return "Coords";
    }

    @Override
    public String getValue() {
        if(UtilityClient.streamerMode) return "<disabled>";

        return    mc().thePlayer.getPosition().getX() + ", "
                + mc().thePlayer.getPosition().getY() + ", "
                + mc().thePlayer.getPosition().getZ();
    }

    @Override
    public String getAuthor() {
        return "GamingCraft";
    }
}

package org.utilityclient.overlay.modules;

import net.minecraft.client.Minecraft;
import org.utilityclient.overlay.IModule;

public class PingModule implements IModule {
    @Override
    public String getName() {
        return "Ping";
    }

    @Override
    public String getValue() {
        return mc().thePlayer.sendQueue.getPlayerInfo(mc().thePlayer.getUniqueID()).getResponseTime() + "ms";
    }

    @Override
    public String getAuthor() {
        return "GamingCraft";
    }

    @Override
    public boolean shouldRender() {
        return !mc().isSingleplayer();
    }
}

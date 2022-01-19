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
        return Minecraft.getMinecraft().thePlayer.sendQueue.getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime() + "ms";
    }

    @Override
    public String getAuthor() {
        return "GamingCraft";
    }

    @Override
    public boolean shouldRender() {
        return !Minecraft.getMinecraft().isSingleplayer();
    }
}

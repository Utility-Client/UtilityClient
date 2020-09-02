package de.gamingcraft.overlay.modules;

import de.gamingcraft.overlay.IModule;
import net.minecraft.client.Minecraft;

public class PingModule implements IModule {
    @Override
    public String getName() {
        return "Ping";
    }

    @Override
    public String getValue() {
        if(Minecraft.getMinecraft().isIntegratedServerRunning()) return "0ms";
        return Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime() + "ms";
    }

    @Override
    public String getAuthor() {
        return "GamingCraft_hd";
    }
}

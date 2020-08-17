package de.gamingcraft.overlay.modules;

import de.gamingcraft.overlay.IModule;

public class PingModule implements IModule {
    @Override
    public String getName() {
        return "Ping";
    }

    @Override
    public String getValue() {
        //return Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime() + "ms";
        return "0ms";
    }

    @Override
    public String getAuthor() {
        return "GamingCraft_hd";
    }
}

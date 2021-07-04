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
        // TODO
        return "null";
    }

    @Override
    public String getAuthor() {
        return "GamingCraft_hd";
    }
}

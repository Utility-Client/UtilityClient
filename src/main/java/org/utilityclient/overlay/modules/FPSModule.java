package org.utilityclient.overlay.modules;

import org.utilityclient.overlay.IModule;
import net.minecraft.client.Minecraft;

public class FPSModule implements IModule {
    @Override
    public String getName() {
        return "FPS";
    }

    @Override
    public String getValue() {
        return ((Minecraft.getDebugFPS() < 60) ? "§c" : ((Minecraft.getDebugFPS() < 120) ? "§e" : "§a")) + Minecraft.getDebugFPS() + "";
    }

    @Override
    public String getAuthor() {
        return "GamingCraft_hd";
    }
}

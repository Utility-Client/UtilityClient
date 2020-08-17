package de.gamingcraft.overlay.modules;

import de.gamingcraft.overlay.IModule;
import net.minecraft.client.Minecraft;

public class FPSModule implements IModule {
    @Override
    public String getName() {
        return "FPS";
    }

    @Override
    public String getValue() {
        return Minecraft.getDebugFPS() + "";
    }

    @Override
    public String getAuthor() {
        return "GamingCraft_hd";
    }
}

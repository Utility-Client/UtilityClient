package org.utilityclient.overlay.modules;

import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.overlay.IModule;
import net.minecraft.client.Minecraft;

public class FPSModule extends IModule {
    @Override
    public String getName() {
        return "FPS";
    }

    @Override
    public String getValue() {
        String red = EnumChatFormatting.RED + "";
        String yellow = EnumChatFormatting.YELLOW + "";
        String green = EnumChatFormatting.GREEN + "";

        return ((Minecraft.getDebugFPS() < 60) ? red : ((Minecraft.getDebugFPS() < 120) ? yellow : green)) + Minecraft.getDebugFPS() + "";
    }

    @Override
    public String getAuthor() {
        return "GamingCraft_hd";
    }
}

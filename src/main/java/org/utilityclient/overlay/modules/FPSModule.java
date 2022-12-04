package org.utilityclient.overlay.modules;

import net.minecraft.util.ChatFormatting;
import org.utilityclient.overlay.IModule;
import net.minecraft.client.Minecraft;

public class FPSModule extends IModule {
    @Override
    public String getName() {
        return "FPS";
    }

    @Override
    public String getValue() {
        String red = ChatFormatting.RED + "";
        String yellow = ChatFormatting.YELLOW + "";
        String green = ChatFormatting.GREEN + "";

        return ((Minecraft.getDebugFPS() < 60) ? red : ((Minecraft.getDebugFPS() < 120) ? yellow : green)) + Minecraft.getDebugFPS() + "";
    }

    @Override
    public String getAuthor() {
        return "GamingCraft";
    }
}

package org.utilityclient.overlay.modules;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.MinecraftClient;
import org.utilityclient.api.Module;

public class FPSModule extends Module {
    @Override
    public String getName() {
        return "FPS";
    }

    @Override
    public String getValue() {
        String red = ChatFormatting.RED + "";
        String yellow = ChatFormatting.YELLOW + "";
        String green = ChatFormatting.GREEN + "";

        int fps = MinecraftClient.getCurrentFps();
        return ((fps < 60) ? red : ((fps < 120) ? yellow : green)) + fps + "";
    }

    @Override
    public String getAuthor() {
        return "Sam302";
    }
}

package org.utilityclient.overlay.modules;

import net.minecraft.client.MinecraftClient;
import org.utilityclient.api.Module;
import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;

@Register @StandaloneCompatible
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

package org.utilityclient.overlay;

import static net.minecraft.client.Minecraft.getMinecraft;

import net.minecraft.client.gui.widget.ButtonWidget;
import org.utilityclient.config.Config;
import net.minecraft.client.gui.ButtonWidget;
import net.minecraft.client.gui.ScaledResolution;

public class Keystrokes {
    public static void loop() {
        if(!Config.getBoolean("keystrokesEnabled", true)) return;
        ScaledResolution sr = new ScaledResolution(getMinecraft());
        new ButtonWidget(0, sr.getScaledWidth()-48, sr.getScaledHeight()-72, 20, 20,
                "W", getMinecraft().gameSettings.keyBindForward.isKeyDown()).drawButton(getMinecraft(), 0, 0);
        new ButtonWidget(0, sr.getScaledWidth()-72, sr.getScaledHeight()-48, 20, 20,
                "A", getMinecraft().gameSettings.keyBindLeft.isKeyDown()).drawButton(getMinecraft(), 0, 0);
        new ButtonWidget(0, sr.getScaledWidth()-48, sr.getScaledHeight()-48, 20, 20,
                "S", getMinecraft().gameSettings.keyBindBack.isKeyDown()).drawButton(getMinecraft(), 0, 0);
        new ButtonWidget(0, sr.getScaledWidth()-24, sr.getScaledHeight()-48, 20, 20,
                "D", getMinecraft().gameSettings.keyBindRight.isKeyDown()).drawButton(getMinecraft(), 0, 0);
        new ButtonWidget(0, sr.getScaledWidth()-72, sr.getScaledHeight()-24, 68, 20,
                "Space", getMinecraft().gameSettings.keyBindJump.isKeyDown()).drawButton(getMinecraft(), 0, 0);
    }
}

package de.gamingcraft.crosshair;

import de.gamingcraft.config.ConfigManager;
import de.gamingcraft.utils.SerializationUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;

import java.io.IOException;
import java.util.HashMap;

public class CrosshairManager extends Thread {

    @Override
    public void run() {
        super.run();
    }

    public void loop() throws IOException, ClassNotFoundException {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int centerX = sr.getScaledWidth() / 2;
        int centerY = sr.getScaledHeight() / 2;
        int scale = 1;
        HashMap<Integer, Boolean> pixels = (HashMap<Integer, Boolean>) SerializationUtils.deserialize(ConfigManager.config.getCrosshair());
        int size = ConfigManager.config.getCrosshairSize();

        int f = 0;
        for (int i = 0; i < size; i++) {
            for (int e = 0; e < size; e++) {
                if(pixels.get(f)) {
                    int x = centerX + i * scale;
                    int y = centerY + e * scale;
                }
                f++;
            }
        }

    }
}

package de.gamingcraft.crosshair;

import de.gamingcraft.config.ConfigManager;
import de.gamingcraft.utils.SerializationUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import java.util.HashMap;

import static net.minecraft.client.gui.Gui.drawRect;

public class CrosshairManager extends Thread {

    @Override
    public void run() {
        super.run();
    }

    public void loop() throws Exception {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int centerX = sr.getScaledWidth() / 2;
        int centerY = sr.getScaledHeight() / 2;
        HashMap<Integer, Boolean> pixels = (HashMap<Integer, Boolean>) SerializationUtils.deserialize(ConfigManager.config.getCrosshair());
        int size = ConfigManager.config.getCrosshairSize();



        int f = 0;
        for (int i = 0; i < size; i++) {
            for (int e = 0; e < size; e++) {
                int x = centerX + i * 20 - size * 10;
                int y = centerY + e * 20 - size * 10;
                drawRect(x, y, x+20, y+20, 0);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(pixels.getOrDefault(f, true) ? "" : "X", x, y, 0);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(x + " - " + y, 2, centerY + (f * 10) - (f * 5), 0);
                f++;
            }
        }

    }
}

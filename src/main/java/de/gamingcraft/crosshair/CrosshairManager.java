package de.gamingcraft.crosshair;

import de.gamingcraft.config.ConfigManager;
import de.gamingcraft.utils.SerializationUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.util.HashMap;

public class CrosshairManager {

    public static void loop(ScaledResolution sr) throws Exception {
        GlStateManager.disableDepth();
        GlStateManager.disableAlpha();
        HashMap<Integer, Boolean> pixels = (HashMap<Integer, Boolean>) SerializationUtils.deserialize(ConfigManager.config.getCrosshair());
        int size = ConfigManager.config.getCrosshairSize();

        int f = 0;
        for (int i = 0; i < size; i++) {
            for (int e = 0; e < size; e++) {
                int x = sr.getScaledWidth() / 2 + i * 2 - size;
                int y = sr.getScaledHeight() / 2 + e * 2 - size;
                if(!pixels.getOrDefault(f, true)) Gui.drawRect(x, y, x + 2, y + 2, 2164260863L);
                f++;
            }
        }
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
    }
}

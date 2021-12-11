package de.gamingcraft.crosshair;

import de.gamingcraft.config.Config;
import de.gamingcraft.config.ConfigEntry;
import de.gamingcraft.gui.GuiCrosshairOptions;
import de.gamingcraft.utils.SerializationUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CrosshairManager {
    public static HashMap<Integer, Boolean> pixels;
    public static void run() throws IOException, ClassNotFoundException {
        if(GuiCrosshairOptions.crosshairFile.createNewFile()) {
            FileWriter fw = new FileWriter(GuiCrosshairOptions.crosshairFile, false);
            fw.write(SerializationUtils.serialize(new HashMap<Integer, Boolean>()));
            fw.close();
        }

        Scanner scanner = new Scanner(GuiCrosshairOptions.crosshairFile);
        pixels = (HashMap<Integer, Boolean>) SerializationUtils.deserialize(scanner.nextLine());
        scanner.close();
    }

    public static void loop(ScaledResolution sr) throws Exception {
        GlStateManager.disableDepth();
        GlStateManager.disableAlpha();
        int size = Config.getInteger(ConfigEntry.CROSSHAIR_SIZE, 9);

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

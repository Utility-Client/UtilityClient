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
            fw.write("rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAABh3CAAAACAAAAANc3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAANzcgARamF2YS5sYW5nLkJvb2xlYW7NIHKA1Zz67gIAAVoABXZhbHVleHAAc3EAfgACAAAAJnEAfgAGc3EAfgACAAAACnEAfgAGc3EAfgACAAAALXEAfgAGc3EAfgACAAAAEXEAfgAGc3EAfgACAAAAFXEAfgAGc3EAfgACAAAAFnEAfgAGc3EAfgACAAAAF3EAfgAGc3EAfgACAAAAGHEAfgAGc3EAfgACAAAAGXEAfgAGc3EAfgACAAAAGnEAfgAGc3EAfgACAAAAG3EAfgAGc3EAfgACAAAAH3EAfgAGeA==");
            fw.close();
            Config.setInteger(ConfigEntry.CROSSHAIR_SIZE, 7); // Override to prevent unwanted behavior.
        }

        try {
            Scanner scanner = new Scanner(GuiCrosshairOptions.crosshairFile);
            pixels = (HashMap<Integer, Boolean>) SerializationUtils.deserialize(scanner.nextLine());
            scanner.close();
        } catch (Exception e) {
            pixels = (HashMap<Integer, Boolean>) SerializationUtils.deserialize("rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAABh3CAAAACAAAAANc3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAANzcgARamF2YS5sYW5nLkJvb2xlYW7NIHKA1Zz67gIAAVoABXZhbHVleHAAc3EAfgACAAAAJnEAfgAGc3EAfgACAAAACnEAfgAGc3EAfgACAAAALXEAfgAGc3EAfgACAAAAEXEAfgAGc3EAfgACAAAAFXEAfgAGc3EAfgACAAAAFnEAfgAGc3EAfgACAAAAF3EAfgAGc3EAfgACAAAAGHEAfgAGc3EAfgACAAAAGXEAfgAGc3EAfgACAAAAGnEAfgAGc3EAfgACAAAAG3EAfgAGc3EAfgACAAAAH3EAfgAGeA==");
            Config.setInteger(ConfigEntry.CROSSHAIR_SIZE, 7); // Override to prevent unwanted behavior.
        }

    }

    public static void loop(ScaledResolution sr) throws Exception {
        GlStateManager.disableDepth();
        GlStateManager.disableAlpha();
        int size = Config.getInteger(ConfigEntry.CROSSHAIR_SIZE);

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

package de.gamingcraft.crosshair;

import de.gamingcraft.config.Config;
import de.gamingcraft.config.ConfigEntry;
import de.gamingcraft.utils.SerializationUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.util.HashMap;

public class CrosshairManager {

    public static void loop(ScaledResolution sr) throws Exception {
        GlStateManager.disableDepth();
        GlStateManager.disableAlpha();
        //HashMap<Integer, Boolean> pixels = (HashMap<Integer, Boolean>) SerializationUtils.deserialize(Config.getString(ConfigEntry.CROSSHAIR_DATA, "rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAADB3CAAAAEAAAAAdc3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAAFzcgARamF2YS5sYW5nLkJvb2xlYW7NIHKA1Zz67gIAAVoABXZhbHVleHAAc3EAfgACAAAAAnEAfgAGc3EAfgACAAAAQ3EAfgAGc3EAfgACAAAABHEAfgAGc3EAfgACAAAABnEAfgAGc3EAfgACAAAAR3EAfgAGc3EAfgACAAAAB3EAfgAGc3EAfgACAAAASXEAfgAGc3EAfgACAAAACXEAfgAGc3EAfgACAAAASnEAfgAGc3EAfgACAAAATHEAfgAGc3EAfgACAAAADXEAfgAGc3EAfgACAAAATnEAfgAGc3EAfgACAAAAT3EAfgAGc3EAfgACAAAAEXEAfgAGc3EAfgACAAAAEnEAfgAGc3EAfgACAAAAFnEAfgAGc3EAfgACAAAAGnEAfgAGc3EAfgACAAAAJHEAfgAGc3EAfgACAAAAJXEAfgAGc3EAfgACAAAAJnEAfgAGc3EAfgACAAAAKHEAfgAGc3EAfgACAAAAKnEAfgAGc3EAfgACAAAAK3EAfgAGc3EAfgACAAAALHEAfgAGc3EAfgACAAAANnEAfgAGc3EAfgACAAAAOnEAfgAGc3EAfgACAAAAPnEAfgAGc3EAfgACAAAAP3EAfgAGeA\\=\\="));
        int size = Config.getInteger(ConfigEntry.CROSSHAIR_SIZE, 9);

        int f = 0;
        for (int i = 0; i < size; i++) {
            for (int e = 0; e < size; e++) {
                int x = sr.getScaledWidth() / 2 + i * 2 - size;
                int y = sr.getScaledHeight() / 2 + e * 2 - size;
                //if(!pixels.getOrDefault(f, true)) Gui.drawRect(x, y, x + 2, y + 2, 2164260863L);
                f++;
            }
        }
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
    }
}

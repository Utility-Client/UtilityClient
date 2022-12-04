package org.utilityclient.crosshair;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.gui.options.GuiCrosshairOptions;
import org.utilityclient.utils.SerializationUtils;

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

    public static void loop() {
        GlStateManager.disableDepthTest();
        GlStateManager.disableAlphaTest();
        int size = Config.getInteger(ConfigEntry.CROSSHAIR_SIZE);

        int f = 0;
        for (int i = 0; i < size; i++) {
            for (int e = 0; e < size; e++) {
                int x = MinecraftClient.getInstance().width / 2 + i * 2 - size;
                int y = MinecraftClient.getInstance().height / 2 + e * 2 - size;
                if(!pixels.getOrDefault(f, true)) DrawableHelper.fill(x, y, x + 2, y + 2, (int) Config.getLong(ConfigEntry.CROSSHAIR_COLOR));
                f++;
            }
        }
        GlStateManager.enableDepthTest();
        GlStateManager.enableAlphaTest();
    }
}

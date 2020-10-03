package de.gamingcraft.crosshair;

import de.gamingcraft.config.ConfigManager;
import de.gamingcraft.crosshair.crosshairs.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.ArrayList;

public class CrosshairManager extends Thread {
    public ArrayList<Crosshair> crosshairs = new ArrayList<Crosshair>();

    @Override
    public void run() {
        crosshairs.add(new DefaultCrosshair());
        crosshairs.add(new PointCross());
        crosshairs.add(new PointCrosshair());
        super.run();
    }

    public void loop(int offsetY) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int centerX = sr.getScaledWidth() / 2;
        int centerY = sr.getScaledHeight() / 2 + offsetY;

        int scaleFactor = 1;

        switch (sr.getScaleFactor()) {
            case 1:
                scaleFactor = 3;
                break;
            case 2:
                scaleFactor = 2;
                break;
            case 3:
                scaleFactor = 1;
                break;
        }

        crosshairs.get(ConfigManager.config.getCrosshair()).updateRender(sr, centerX, centerY, scaleFactor);
    }
}

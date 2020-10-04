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

        crosshairs.get(ConfigManager.config.getCrosshair()).updateRender(sr, centerX, centerY, sr.getScaleFactor());
    }
}

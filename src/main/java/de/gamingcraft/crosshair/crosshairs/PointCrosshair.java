package de.gamingcraft.crosshair.crosshairs;

import de.gamingcraft.crosshair.Crosshair;
import net.minecraft.client.gui.ScaledResolution;

import static net.minecraft.client.gui.Gui.drawRect;

public class PointCrosshair implements Crosshair {
    @Override
    public String getName() {
        return "Point Crosshair";
    }

    @Override
    public void updateRender(ScaledResolution sr, int centerX, int centerY, int scaleFactor) {
        double scale = 0.2f * scaleFactor;
        drawRect(centerX-scale, centerY-scale, centerX+scale, centerY+scale,-1);
    }
}

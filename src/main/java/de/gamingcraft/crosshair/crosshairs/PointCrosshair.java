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
        drawRect(centerX-0.5f, centerY-0.5f, centerX+0.5f, centerY+0.5f, -1);
    }
}

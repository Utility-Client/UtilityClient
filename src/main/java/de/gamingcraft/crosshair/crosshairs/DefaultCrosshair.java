package de.gamingcraft.crosshair.crosshairs;

import de.gamingcraft.crosshair.Crosshair;
import net.minecraft.client.gui.ScaledResolution;

import static net.minecraft.client.gui.Gui.drawRect;

public class DefaultCrosshair implements Crosshair {
    @Override
    public String getName() {
        return "Cross Crosshair";
    }

    @Override
    public void updateRender(ScaledResolution sr, int centerX, int centerY, int scaleFactor) {
        double scaleX = 3.5f * scaleFactor;
        double scaleY = 0.2f * scaleFactor;

        drawRect(centerX-scaleX, centerY-scaleY, centerX+scaleX, centerY+scaleY,-1);
        drawRect(centerX-scaleY, centerY-scaleX, centerX+scaleY, centerY+scaleX,-1);
    }
}

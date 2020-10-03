package de.gamingcraft.crosshair.crosshairs;

import de.gamingcraft.crosshair.Crosshair;
import net.minecraft.client.gui.ScaledResolution;

import static net.minecraft.client.gui.Gui.drawRect;

public class PointCross implements Crosshair {
    @Override
    public String getName() {
        return "Point-Cross Crosshair";
    }

    @Override
    public void updateRender(ScaledResolution sr, int centerX, int centerY, int scaleFactor) {
        double scaleY = 0.175f * scaleFactor;
        double scaleX = 3.5f * scaleFactor;

        drawRect(centerX-scaleY, centerY-scaleY, centerX+scaleY, centerY+scaleY,-1);

        drawRect((centerX-scaleX*2), centerY-scaleY, centerX - 2/*+scaleX*/, centerY+scaleY,-1);
        drawRect(centerX-scaleY, (centerY-scaleX*2), centerX+scaleY, centerY - 2,-1);

        drawRect((centerX+scaleX*2), centerY-scaleY, centerX + 2/*+scaleX*/, centerY+scaleY,-1);
        drawRect(centerX-scaleY, (centerY+scaleX*2), centerX+scaleY, centerY + 2,-1);
    }
}

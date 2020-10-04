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
        drawRect(centerX-1, centerY-1, centerX+1, centerY+1, -1);

        drawRect(centerX - 10, centerY - 1, centerX - 4, centerY + 1, -1);

        drawRect(centerX + 10, centerY + 1, centerX + 4, centerY - 1, -1);

        drawRect(centerX - 1, centerY + 10, centerX + 1, centerY + 4, -1);

        drawRect(centerX + 1, centerY - 10, centerX - 1, centerY - 4, -1);
    }
}

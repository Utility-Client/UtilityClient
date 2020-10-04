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
        drawRect(centerX-0.5f, centerY-0.5f, centerX+0.5f, centerY+0.5f, -1);

        drawRect(centerX - 10, centerY - 0.5f, centerX - 4, centerY + 0.5f, -1);

        drawRect(centerX + 10, centerY + 0.5f, centerX + 4, centerY - 0.5f, -1);

        drawRect(centerX - 0.5f, centerY + 10, centerX + 0.5f, centerY + 4, -1);

        drawRect(centerX + 0.5f, centerY - 10, centerX - 0.5f, centerY - 4, -1);
    }
}

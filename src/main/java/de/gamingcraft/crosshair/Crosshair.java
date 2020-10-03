package de.gamingcraft.crosshair;

import net.minecraft.client.gui.ScaledResolution;

public interface Crosshair {
    String getName();
    void updateRender(ScaledResolution sr, int centerX, int centerY, int scaleFactor);
}

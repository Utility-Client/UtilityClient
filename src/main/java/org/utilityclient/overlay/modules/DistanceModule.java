package org.utilityclient.overlay.modules;

import org.utilityclient.overlay.IModule;

public class DistanceModule extends IModule {
    public static int x, y, z;
    public static boolean gotUpdated = false;

    @Override
    public String getName() {
        return "Distance";
    }

    @Override
    public String getValue() {
        double px, py, pz;
        px = mc().player.getPos().x;
        py = mc().player.getPos().y;
        pz = mc().player.getPos().z;
        double dx = px - x, dy = py - y, dz = pz - z;
        return (dx + ", " + dy + ", " + dz).replaceAll("-", "");
    }

    @Override
    public String getAuthor() {
        return "Sam302";
    }

    @Override
    public boolean shouldRender() {
        return gotUpdated;
    }
}

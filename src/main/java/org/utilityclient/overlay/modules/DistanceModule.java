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
        int px, py, pz;
        px = mc().thePlayer.getPosition().getX();
        py = mc().thePlayer.getPosition().getY();
        pz = mc().thePlayer.getPosition().getZ();
        int dx = px - x, dy = py - y, dz = pz - z;
        return (dx + ", " + dy + ", " + dz).replaceAll("-", "");
    }

    @Override
    public String getAuthor() {
        return "GamingCraft";
    }

    @Override
    public boolean shouldRender() {
        return gotUpdated;
    }
}

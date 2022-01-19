package org.utilityclient.overlay.modules;

import org.utilityclient.overlay.IModule;

public class BiomeModule extends IModule {

    @Override
    public String getName() {
        return "Biome";
    }

    @Override
    public String getValue() {
        return mc().theWorld.getChunkFromBlockCoords(mc().thePlayer.getPosition()).getBiome(mc().thePlayer.getPosition(), mc().theWorld.getWorldChunkManager()).biomeName;
    }

    @Override
    public String getAuthor() {
        return "GamingCraft";
    }
}

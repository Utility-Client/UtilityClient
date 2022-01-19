package org.utilityclient.overlay.modules;

import org.utilityclient.overlay.IModule;
import net.minecraft.client.Minecraft;

public class BiomeModule implements IModule {

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
        return "GamingCraft_hd";
    }
}

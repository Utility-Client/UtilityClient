package org.utilityclient.overlay.modules;

import org.utilityclient.api.Module;

public class BiomeModule extends Module {

    @Override
    public String getName() {
        return "Biome";
    }

    @Override
    public String getValue() {
        return mc().world.getChunk(mc().player.getBlockPos()).getBiomeAt(mc().player.getBlockPos(), mc().world.getBiomeSource()).name;
    }

    @Override
    public String getAuthor() {
        return "Sam302";
    }
}

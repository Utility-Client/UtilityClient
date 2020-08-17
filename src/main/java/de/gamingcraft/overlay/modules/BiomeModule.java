package de.gamingcraft.overlay.modules;

import de.gamingcraft.overlay.IModule;
import net.minecraft.client.Minecraft;

public class BiomeModule implements IModule {
    @Override
    public String getName() {
        return "Biome";
    }

    @Override
    public String getValue() {
        return Minecraft.getMinecraft().theWorld.getBiomeGenForCoords(Minecraft.getMinecraft().thePlayer.playerLocation).biomeName;
    }

    @Override
    public String getAuthor() {
        return "GamingCraft_hd";
    }
}

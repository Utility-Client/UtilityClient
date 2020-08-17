package de.gamingcraft.overlay.modules;

import de.gamingcraft.overlay.IModule;
import net.minecraft.client.Minecraft;

public class CoordsModule implements IModule {
    @Override
    public String getName() {
        return "Coords";
    }

    @Override
    public String getValue() {
        return    Minecraft.getMinecraft().thePlayer.getPosition().getX() + ", "
                + Minecraft.getMinecraft().thePlayer.getPosition().getY() + ", "
                + Minecraft.getMinecraft().thePlayer.getPosition().getZ();
    }

    @Override
    public String getAuthor() {
        return "GamingCraft_hd";
    }
}

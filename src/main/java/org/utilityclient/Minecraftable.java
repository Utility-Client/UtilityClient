package org.utilityclient;

import net.minecraft.client.Minecraft;

public abstract class Minecraftable {
    Minecraft mc = Minecraft.getMinecraft();
    public Minecraft mc() {
        return mc;
    }
}

package org.utilityclient;

import net.minecraft.client.Minecraft;

public abstract class Instances {
    Minecraft mc = Minecraft.getMinecraft();
    UtilityClient uc = UtilityClient.getInstance();
    public Minecraft mc() {
        return mc;
    }
    public UtilityClient uc() {
        return uc;
    }
}

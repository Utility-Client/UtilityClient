package org.utilityclient;

import net.minecraft.client.MinecraftClient;

/**
 * Instances of some Main Classes.
 * Including: UtilityClient's Main Class and Minecraft's Client Main Class.
 * @since 2.10 LTS
 * @author GamingCraft
 */
public abstract class Instances {
    MinecraftClient mc = MinecraftClient.getInstance();
    UtilityClient uc = UtilityClient.getInstance();
    public MinecraftClient mc() {
        return mc;
    }
    public UtilityClient uc() {
        return uc;
    }

    /**
     * Interface version without caching.
     * @since 2.15 LTS
     * @author GamingCraft
     * @see Instances
     */
    public interface Interface {
        default MinecraftClient mc() {return MinecraftClient.getInstance();}
        default UtilityClient uc() {return UtilityClient.getInstance();}
    }
}
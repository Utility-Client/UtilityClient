package org.utilityclient.api;

import net.minecraft.client.MinecraftClient;
import org.utilityclient.UtilityClient;

/**
 * Instances of some Main Classes.
 * Including: UtilityClient's Main Class and Minecraft's Client Main Class.
 *
 * @author Sam302
 * @since 2.10 LTS
 */
public abstract class Instances {
    @Deprecated
    MinecraftClient mc = MinecraftClient.getInstance();
    UtilityClient uc = UtilityClient.getInstance();
    @Deprecated
    public MinecraftClient mc() {
        return mc;
    }
    public UtilityClient uc() {
        return uc;
    }

    /**
     * Interface version without caching.
     * @since 2.15 LTS
     * @author Sam302
     * @see Instances
     */
    public interface Interface {
        @Deprecated
        default MinecraftClient mc() {return MinecraftClient.getInstance();}
        default UtilityClient uc() {return UtilityClient.getInstance();}
    }
}
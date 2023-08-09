package org.utilityclient.api;

import net.minecraft.client.MinecraftClient;
import org.utilityclient.UtilityClient;
import org.utilityclient.api.abstraction.StandaloneCompatible;

/**
 * Instances of some Main Classes.
 * Including: UtilityClient's Main Class and Minecraft's Client Main Class.
 *
 * @author Sam302
 * @since 2.10 LTS
 */
@StandaloneCompatible
public abstract class Instances {
    UtilityClient uc = UtilityClient.getInstance();

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
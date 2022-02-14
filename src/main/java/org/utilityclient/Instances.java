package org.utilityclient;

import net.minecraft.client.Minecraft;

/**
 * Instances of some Main Classes.
 * Including: UtilityClient's Main Class and Minecraft's Client Main Class.
 * @since 2.10 LTS
 * @author GamingCraft
 */
public abstract class Instances {
    Minecraft mc = Minecraft.getMinecraft();
    UtilityClient uc = UtilityClient.getInstance();
    public Minecraft mc() {
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
        default Minecraft mc() {return Minecraft.getMinecraft();}
        default UtilityClient uc() {return UtilityClient.getInstance();}
    }
}
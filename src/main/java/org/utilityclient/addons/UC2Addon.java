package org.utilityclient.addons;

import org.utilityclient.Instances;

/**
 * @author GamingCraft
 * @since 2.15 LTS
 * @see Instances.Interface
 * @implNote Use mc() to get the Minecraft client instance and uc() to get the UtilityClient instance.
 */
public interface UC2Addon extends Instances.Interface {
    /**
     * @return Name of the Add-on. This will be displayed to users.
     * @implNote Color coding using {@link net.minecraft.util.EnumChatFormatting} is allowed.
     */
    String getName();

    /**
     * @return The author(s) of the Add-on.
     */
    String getAuthor();

    /**
     * @return The current version of the Add-on.
     * @implSpec Please follow <a href="https://semver.org/">these guidelines.</a>
     */
    String getVersion();

    /**
     * Gets called on startup.
     * Register your themes, keybindings and modules here.
     * @since 2.15 LTS
     */
    default void onStartEvent() {}

    /**
     * Gets called every frame.
     * @since 2.15 LTS
     */
    default void onUpdateEvent() {}
}

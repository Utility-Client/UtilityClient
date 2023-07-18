package org.utilityclient.addons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.utilityclient.Instances;

/**
 * @author Sam302
 * @implNote Use mc() to get the Minecraft client instance and uc() to get the UtilityClient instance. Directly calling UtilityClient for static methods is allowed too.
 * @see Instances.Interface
 * @since 2.15 LTS
 */
public interface UC2Addon extends Instances.Interface {
    /**
     * @return Name of the Add-on. This will be displayed to users.
     * @implNote Color coding using {@link com.mojang.realmsclient.gui.ChatFormatting} is allowed.
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

    /**
     * Do not override.
     * Use this as logger.
     * @return A Log4J2 Logger object
     */
    default Logger getLogger() {
        return LogManager.getLogger();
    }
}

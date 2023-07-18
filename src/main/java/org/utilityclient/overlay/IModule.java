package org.utilityclient.overlay;

import org.utilityclient.Instances;

/**
 * @author Sam302
 * @since 2.0 LTS
 */
public abstract class IModule extends Instances {
    /**
     * Controlled by GuiOverlaySettings.
     * Do not override.
     * @since 2.13
     */
    public boolean isEnabled = true;

    /**
     * Used as prefix.
     * @return The name of the module.
     * @since 2.0 LTS
     */
    public abstract String getName();

    /**
     * Used as suffix.
     * @return The value of the module.
     * @since 2.0 LTS
     */
    public abstract String getValue();

    /**
     * @return Your name, isn't shown anywhere yet.
     * @since 2.0 LTS
     */
    public abstract String getAuthor();

    /**
     * @return Should currently render?
     * @implNote Used for modules, that might be disabled. Examples might be a Multiplayer-only module.
     * @since 2.13
     */
    public boolean shouldRender() {
        return true;
    }
}

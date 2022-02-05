package org.utilityclient.overlay;

import org.utilityclient.Instances;

public abstract class IModule extends Instances {
    /**
     * Controlled by GuiOverlaySettings.
     * Do not override.
     */
    public boolean isEnabled = true;

    /**
     * Used as prefix.
     * @return The name of the module.
     */
    public abstract String getName();

    /**
     * Used as suffix.
     * @return The value of the module.
     */
    public abstract String getValue();

    /**
     * @return Your name, isn't shown anywhere yet.
     */
    public abstract String getAuthor();

    /**
     * @return Should currently render?
     * @implNote Used for modules, that might be disabled. Examples might be a Multiplayer-only module.
     */
    public boolean shouldRender() {
        return true;
    }
}

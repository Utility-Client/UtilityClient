package org.utilityclient.api;

import org.utilityclient.UtilityClient;
import org.utilityclient.overlay.ModuleHandler;

/**
 * @apiNote Previously known as "IModule", refactored in 3.0 LTS
 * @author Sam302
 * @since 2.0 LTS
 */
public abstract class Module extends Registrable {
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

    @Override
    public void register() {
        ModuleHandler.modules.add(this);
    }
}
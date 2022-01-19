package org.utilityclient.overlay;

import org.utilityclient.Instances;

public abstract class IModule extends Instances {
    public abstract String getName();

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

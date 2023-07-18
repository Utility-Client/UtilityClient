package org.utilityclient.gui;

import net.minecraft.client.gui.screen.Screen;

/**
 * @since 3.0
 */
public class UCScreen extends Screen {
    /**
     * @see UCScreen#getActionLabel()
     */
    private final String actionLabel;

    public UCScreen(String actionLabel) {
        this.actionLabel = actionLabel;
    }

    /**
     * @return What the player is currently doing. Override if dynamic
     * @since 3.0
     */
    public String getActionLabel() {
        return actionLabel;
    }
}

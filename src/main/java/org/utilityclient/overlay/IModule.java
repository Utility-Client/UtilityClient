package org.utilityclient.overlay;

import net.minecraft.client.Minecraft;

public interface IModule {
    /**
     * @return The name of the module, shown as prefix ingame.
     */
    String getName();

    /**
     * @return The current value of the module. Shown as suffix.
     */
    String getValue();

    /**
     * @return Your name, isn't shown anywhere yet.
     */
    String getAuthor();

    /**
     * @return Should currently render?
     * @implNote Used for modules, that might be disabled. Examples might be a Multiplayer-only module.
     */
    default boolean shouldRender() {
        return true;
    }

    default Minecraft mc() {
        return Minecraft.getMinecraft();
    }
}

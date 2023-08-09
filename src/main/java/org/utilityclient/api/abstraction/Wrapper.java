package org.utilityclient.api.abstraction;

import org.utilityclient.UtilityClient;

/**
 * Wrapper for all methods outside UtilityClient's scope. (LWJGL, Minecraft, ...)
 * @since 3.0
 * @author Sam302
 */
public abstract class Wrapper {
    public Wrapper() {
        UtilityClient.setInstance(new UtilityClient(this));
    }

    public abstract boolean isKeyDown(int keyCode);
    public abstract void sendChatMessage(String msg);
    public abstract void writeChatMessage(String msg);
}

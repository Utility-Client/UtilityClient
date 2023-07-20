package org.utilityclient.overlay.modules;

import org.utilityclient.overlay.IModule;

public class PingModule extends IModule {
    @Override
    public String getName() {
        return "Ping";
    }

    @Override
    public String getValue() {
        try {
            return mc().player.networkHandler.getPlayerListEntry(mc().player.getUuid()).getLatency() + "ms";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    public String getAuthor() {
        return "Sam302";
    }

    @Override
    public boolean shouldRender() {
        return !mc().isInSingleplayer();
    }
}

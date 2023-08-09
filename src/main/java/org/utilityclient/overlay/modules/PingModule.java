package org.utilityclient.overlay.modules;

import org.utilityclient.api.Module;
import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;

@Register @StandaloneCompatible
public class PingModule extends Module {
    @Override
    public String getName() {
        return "Ping";
    }

    @Override
    public String getValue() {
        try {
            return uc().wrapper.getLatency() + "ms";
        } catch (Exception e) {
            e.printStackTrace();
            return "-/-";
        }
    }

    @Override
    public String getAuthor() {
        return "Sam302";
    }

    @Override
    public boolean shouldRender() {
        return !uc().wrapper.isSingleplayer();
    }
}

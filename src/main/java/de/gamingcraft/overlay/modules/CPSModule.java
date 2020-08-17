package de.gamingcraft.overlay.modules;

import de.gamingcraft.UtilityClient;
import de.gamingcraft.overlay.IModule;

public class CPSModule implements IModule {
    @Override
    public String getName() {
        return "CPS";
    }

    @Override
    public String getValue() {
        return UtilityClient.CPS_THREAD_INSTANCE.getClicks(true) + " | " + UtilityClient.CPS_THREAD_INSTANCE.getClicks(false);
    }

    @Override
    public String getAuthor() {
        return "Hexer";
    }
}


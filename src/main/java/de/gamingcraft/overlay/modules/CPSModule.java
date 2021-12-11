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
        int left = UtilityClient.CPS_THREAD_INSTANCE.getClicks(true);
        int right = UtilityClient.CPS_THREAD_INSTANCE.getClicks(false);

        return ((left < 3) ? "§c" : ((left < 6) ? "§e" : "§a")) + left + " §7| " + ((right < 3) ? "§c" : ((right < 6) ? "§e" : "§a")) + UtilityClient.CPS_THREAD_INSTANCE.getClicks(false);
    }

    @Override
    public String getAuthor() {
        return "Hexer";
    }
}


package org.utilityclient.overlay.modules;

import org.utilityclient.UtilityClient;
import org.utilityclient.api.Module;
import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;

@Register @StandaloneCompatible
public class CPSModule extends Module {
    @Override
    public String getName() {
        return "CPS";
    }

    @Override
    public String getValue() {
        int left = UtilityClient.CPS_THREAD_INSTANCE.getClicks(true);
        int right = UtilityClient.CPS_THREAD_INSTANCE.getClicks(false);

        String red = ChatFormatting.RED + "";
        String yellow = ChatFormatting.YELLOW + "";
        String green = ChatFormatting.GREEN + "";
        String gray = ChatFormatting.GRAY + "";

        return ((left < 3) ? red : ((left < 6) ? yellow : green)) + left + " "+gray+"| " + ((right < 3) ? red : ((right < 6) ? yellow : green)) + UtilityClient.CPS_THREAD_INSTANCE.getClicks(false);
    }

    @Override
    public boolean shouldRender() {
        return false;
    }

    @Override
    public String getAuthor() {
        return "Hexer";
    }
}


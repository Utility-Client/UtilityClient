package org.utilityclient.overlay;

import org.utilityclient.api.Instances;
import org.utilityclient.UtilityClient;
import org.utilityclient.api.Module;
import org.utilityclient.api.Theme;
import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.utils.Color;

import java.util.ArrayList;

@StandaloneCompatible
public class ModuleHandler extends Instances {
    public final static ArrayList<Module> modules = new ArrayList<>();
    private final static int op_x = 4, op_y = 4, mod_height = 9;

    public static void loop() {
        Theme theme = UtilityClient.getCurrentTheme();
        int largest = ucs().wrapper.getStringWidth(theme.getPrefixColor() + UtilityClient.getClientName() + " " + UtilityClient.getVersion());
        int x = 1;

        for (Module mod : modules) {
            if (!mod.isEnabled) continue;
            if (!mod.shouldRender()) continue;
            String str = theme.getPrefixColor() + mod.getName() + theme.getSeparator() + theme.getSuffixColor() + mod.getValue();
            int y = ucs().wrapper.getStringWidth(str);
            if(y > largest) largest = y;
            x++;
        }

        if (Config.getBoolean(ConfigEntry.OVERLAY_BACKGROUND))
            ucs().wrapper.rect(op_x - 2, op_y - 2, largest + op_x + 2, x * mod_height + op_y + 2, Color.Background.color);

        int z = 1;
        for (Module mod : modules) {
            if (!mod.isEnabled) continue;
            if (!mod.shouldRender()) continue;
            String str = theme.getPrefixColor() + mod.getName() + theme.getSeparator() + theme.getSuffixColor() + mod.getValue();
            ucs().wrapper.drawStringWithShadow(str, op_x, op_y + (mod_height*z), Color.SnowWhite.color);
            z++;
        }

        ucs().wrapper.drawStringWithShadow(theme.getPrefixColor() + UtilityClient.getClientName() + " " + UtilityClient.getVersion(), 4, 4, Color.SnowWhite.color);
    }
}

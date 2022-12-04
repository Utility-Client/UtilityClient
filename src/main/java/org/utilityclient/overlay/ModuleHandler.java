package org.utilityclient.overlay;

import net.minecraft.client.gui.screen.Screen;
import org.utilityclient.Instances;
import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.utils.Color;

import java.util.ArrayList;

public class ModuleHandler extends Instances {
    public final static ArrayList<IModule> modules = new ArrayList<>();
    private final static int op_x = 4, op_y = 4, mod_height = 9;

    public static void loop() {
        ITheme theme = UtilityClient.getCurrentTheme();
        int largest = theme.getFontRenderer().getStringWidth(theme.getPrefixColor() + UtilityClient.getClientName() + " " + UtilityClient.getVersion());
        int x = 1;

        for (IModule mod : modules) {
            if (!mod.isEnabled) continue;
            if (!mod.shouldRender()) continue;
            String str = theme.getPrefixColor() + mod.getName() + theme.getSeparator() + theme.getSuffixColor() + mod.getValue();
            int y = theme.getFontRenderer().getStringWidth(str);
            if(y > largest) largest = y;
            x++;
        }

        if(Config.getBoolean(ConfigEntry.OVERLAY_BACKGROUND)) {
            Screen.fill(op_x - 2, op_y - 2, largest + op_x + 2, x * mod_height + op_y + 2, Color.BACKGROUND.color);
        }

        int z = 1;
        for (IModule mod : modules) {
            if (!mod.isEnabled) continue;
            if (!mod.shouldRender()) continue;
            String str = theme.getPrefixColor() + mod.getName() + theme.getSeparator() + theme.getSuffixColor() + mod.getValue();
            theme.getFontRenderer().drawWithShadow(str, op_x, op_y + (mod_height*z), Color.TEXT.color);
            z++;
        }

        theme.getFontRenderer().drawWithShadow(theme.getPrefixColor() + UtilityClient.getClientName() + " " + UtilityClient.getVersion(), 4, 4, Color.TEXT.color);
    }
}

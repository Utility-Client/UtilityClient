package de.gamingcraft.overlay;

import de.gamingcraft.UtilityClient;
import de.gamingcraft.overlay.modules.*;
import net.minecraft.client.gui.FontRenderer;

public class ModuleHandler {
    private static IModule[] modules = new IModule[] {
            new FPSModule(),
            new CPSModule(),
            new CoordsModule(),
            new PingModule()
            //new BiomeModule()
    };

    public static void start() {

    }

    public static void loop(FontRenderer fr) {

        int op_x = 4;
        int op_y = 4;
        int mod_height = 9;

        fr.drawStringWithShadow(UtilityClient.CURRENT_THEME.getPrefix() + UtilityClient.getName() + " " + UtilityClient.getVersion(), 4, 4, 0);

        int x = 1;

        for (IModule mod : modules) {
            fr.drawStringWithShadow(UtilityClient.CURRENT_THEME.getPrefix() + mod.getName() + "§7: " + UtilityClient.CURRENT_THEME.getSuffix() + mod.getValue(), op_x, op_y + (mod_height*x), 0);
            x++;
        }
    }
}

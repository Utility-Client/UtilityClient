package org.utilityclient.overlay;

import org.utilityclient.Instances;
import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
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
            Gui.drawRect(op_x - 2, op_y - 2, largest + op_x + 2, x * mod_height + op_y + 2, Color.BACKGROUND.color);
        }

        int z = 1;
        for (IModule mod : modules) {
            if (!mod.isEnabled) continue;
            if (!mod.shouldRender()) continue;
            String str = theme.getPrefixColor() + mod.getName() + theme.getSeparator() + theme.getSuffixColor() + mod.getValue();
            theme.getFontRenderer().drawStringWithShadow(str, op_x, op_y + (mod_height*z), Color.TEXT.color);
            z++;
        }

        theme.getFontRenderer().drawStringWithShadow(theme.getPrefixColor() + UtilityClient.getClientName() + " " + UtilityClient.getVersion(), 4, 4, Color.TEXT.color);
    }
}

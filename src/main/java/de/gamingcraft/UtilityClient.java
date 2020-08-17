package de.gamingcraft;

import de.gamingcraft.config.ConfigManager;
import de.gamingcraft.macro.MacroManager;
import de.gamingcraft.overlay.ModuleHandler;
import de.gamingcraft.overlay.Theme;
import de.gamingcraft.overlay.modules.CPSModule;
import de.gamingcraft.overlay.modules.CPSThread;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;

public class UtilityClient {
    private static String CLIENT_NAME = "Utility Client";
    private static String CLIENT_VERSION = "2.0";

    public static float fovModifier = 1.0f;

    public static ArrayList<KeyBinding> keyBinds = new ArrayList<>();

    public static final CPSThread CPS_THREAD_INSTANCE = new CPSThread();

    public static Theme CURRENT_THEME = Theme.RED;

    public static void startup() throws IOException {
        //DISCORD_INSTANCE.start();
        CPS_THREAD_INSTANCE.start();
        ModuleHandler.start();
        ConfigManager.start();
        MacroManager.start();
        CURRENT_THEME = Theme.getThemeById(ConfigManager.config.getSelectedTheme());
        addKeyBind("Zoom", ConfigManager.config.getHotkeyZoom(), false);
        addKeyBind("Fulbright", ConfigManager.config.getHotkeyFulbright(), false);
    }

    public static void loop() {
        if(keyBinds.get(0).isKeyDown()) {
            fovModifier = 0.15f;
        }else {
            fovModifier = 1.0f;
        }

        if(keyBinds.get(1).isPressed()) {
            if(Minecraft.getMinecraft().gameSettings.gammaSetting == 1.0f) {
                Minecraft.getMinecraft().gameSettings.gammaSetting = 999999;
            } else {
                Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
            }

        }

        MacroManager.loop();
    }

    public static String getName() {
        return CLIENT_NAME;
    }

    public static String getVersion() {
        return CLIENT_VERSION;
    }

    public static KeyBinding addKeyBind(String name, int keyCode, boolean isMacro) {
        String cat = "Utility Client";
        if (isMacro) cat = "Auto-Commands";
        KeyBinding kb = new KeyBinding(name, keyCode, cat);
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, kb);
        if (!isMacro) keyBinds.add(kb);
        return kb;
    }
}

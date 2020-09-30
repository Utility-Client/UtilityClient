package de.gamingcraft;

import de.gamingcraft.config.ConfigManager;
import de.gamingcraft.discord.DiscordRP;
import de.gamingcraft.macro.MacroManager;
import de.gamingcraft.overlay.ModuleHandler;
import de.gamingcraft.overlay.Theme;
import de.gamingcraft.overlay.modules.CPSThread;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;

public class UtilityClient extends Thread {
    private static String CLIENT_NAME = "Utility Client";
    private static String CLIENT_VERSION = "2.4-dev [LTS]";

    private static UtilityClient CLIENT_INSTANCE = new UtilityClient();
    // https://api.github.com/repos/Utility-Client/UtilityClient2/releases/latest

    public static float fovModifier = 1.0f;

    public static ArrayList<KeyBinding> keyBinds = new ArrayList<>();

    public static final CPSThread CPS_THREAD_INSTANCE = new CPSThread();

    public static final DiscordRP DISCORD_INSTANCE = new DiscordRP();

    public static Theme CURRENT_THEME = Theme.RED;

    public static boolean renderOverlay = true;

    public static boolean capesEnabled = true;

    public static UtilityClient getInstance() {
        return CLIENT_INSTANCE;
    }

    public void run() {
        DISCORD_INSTANCE.start();
        CPS_THREAD_INSTANCE.start();
        ModuleHandler.start();
        try {
            ConfigManager.start();
            MacroManager.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CURRENT_THEME = Theme.getThemeById(ConfigManager.config.getSelectedTheme());
        addKeyBind(I18n.format("uc.zoom"), ConfigManager.config.getHotkeyZoom(), false);
        addKeyBind(I18n.format("uc.fulbright"), ConfigManager.config.getHotkeyFulbright(), false);
    }

    public static void loop() {
        DISCORD_INSTANCE.loop();
        if(keyBinds.get(0).isKeyDown()) fovModifier = 0.15f; else fovModifier = 1.0f;
        if(keyBinds.get(1).isPressed()) if(Minecraft.getMinecraft().gameSettings.gammaSetting == 1.0f) Minecraft.getMinecraft().gameSettings.gammaSetting = 999999; else Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
        MacroManager.loop();
    }

    public static String getClientName() {
        return CLIENT_NAME;
    }

    public static String getVersion() {
        return CLIENT_VERSION;
    }

    public static KeyBinding addKeyBind(String name, int keyCode, boolean isMacro) {
        String cat = CLIENT_NAME;
<<<<<<< HEAD
        if (isMacro) cat = "Auto-Commands";
=======
        if (isMacro) cat = /*"Auto-Commands"*/ I18n.format("uc.autocommand");
>>>>>>> 46a8495cc0b058f861a12f6a633e12b9b8344fad
        KeyBinding kb = new KeyBinding(name, keyCode, cat);
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, kb);
        if (!isMacro) keyBinds.add(kb);
        return kb;
    }

    public static boolean shouldRenderOverlay() {
        return renderOverlay;
    }
}

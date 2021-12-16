package org.utilityclient;

import org.utilityclient.addons.AddonManager;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.crosshair.CrosshairManager;
import org.utilityclient.discord.DiscordRP;
import org.utilityclient.macro.MacroManager;
import org.utilityclient.overlay.Theme;
import org.utilityclient.overlay.modules.CPSThread;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UtilityClient extends Thread {
    public static final CPSThread CPS_THREAD_INSTANCE = new CPSThread();
    public static final DiscordRP DISCORD_INSTANCE = new DiscordRP();
    private static final String CLIENT_NAME = "Utility Client";
    private static final String CLIENT_VERSION = "2.9.1-DEV";
    private static final UtilityClient CLIENT_INSTANCE = new UtilityClient();
    public static float fovModifier = 1.0f;
    public static ArrayList<KeyBinding> keyBinds = new ArrayList<>();
    public static Theme CURRENT_THEME = Theme.RED;
    public static boolean renderOverlay = true;
    public static boolean capesEnabled = true;
    public static boolean isSprinting = false;

    public static UtilityClient getInstance() {
        return CLIENT_INSTANCE;
    }
    public static String getDiscordApplicationId() {
        return "742760119984455701";
    }
    public static String getClientName() {
        return CLIENT_NAME;
    }
    public static String getVersion() {
        return CLIENT_VERSION;
    }
    public static boolean shouldRenderOverlay() {
        return renderOverlay;
    }

    public static KeyBinding addKeyBind(String name, int keyCode, boolean isMacro) {
        String cat = CLIENT_NAME;
        if (isMacro) cat = "Macros";
        KeyBinding kb = new KeyBinding(name, keyCode, cat);
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, kb);
        if (!isMacro) keyBinds.add(kb);
        return kb;
    }

    public void run() {
        new File("uc2").mkdirs();
        AddonManager.start();
        try {
            Config.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            CrosshairManager.run();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        CURRENT_THEME = Theme.getThemeById(Config.getInteger(ConfigEntry.SELECTED_THEME));
        addKeyBind("Zoom", Config.getInteger(ConfigEntry.HOTKEY_ZOOM), false);
        addKeyBind("Fulbright", Config.getInteger(ConfigEntry.HOTKEY_FULBRIGHT), false);
        addKeyBind("Toggle Overlay", Config.getInteger(ConfigEntry.HOTKEY_OVERLAY), false);
        DISCORD_INSTANCE.start();
        CPS_THREAD_INSTANCE.start();
        try {
            MacroManager.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        DISCORD_INSTANCE.loop();
        if (keyBinds.get(0).isKeyDown()) fovModifier = Config.getFloat(ConfigEntry.ZOOM_FACTOR, 0.15f);
        else fovModifier = 1.0f;
        if (keyBinds.get(1).isPressed()) if (Minecraft.getMinecraft().gameSettings.gammaSetting == 1.0f)
            Minecraft.getMinecraft().gameSettings.gammaSetting = 999999;
        else Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
        if(keyBinds.get(2).isPressed()) renderOverlay = !renderOverlay;
        AddonManager.runAddonEvent("loop");
        MacroManager.loop();
    }
}

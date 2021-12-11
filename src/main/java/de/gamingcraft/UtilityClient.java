package de.gamingcraft;

import de.gamingcraft.addons.AddonManager;
import de.gamingcraft.config.ConfigManager;
import de.gamingcraft.discord.DiscordRP;
import de.gamingcraft.macro.MacroManager;
import de.gamingcraft.overlay.Keystrokes;
import de.gamingcraft.overlay.Theme;
import de.gamingcraft.overlay.modules.CPSThread;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;

public class UtilityClient extends Thread {
    public static final CPSThread CPS_THREAD_INSTANCE = new CPSThread();
    public static final DiscordRP DISCORD_INSTANCE = new DiscordRP();
    private static final String CLIENT_NAME = "Utility Client";
    private static final String CLIENT_VERSION = "2.9-DEV";
    private static final UtilityClient CLIENT_INSTANCE = new UtilityClient();
    public static float fovModifier = 1.0f;
    public static ArrayList<KeyBinding> keyBinds = new ArrayList<>();
    public static Theme CURRENT_THEME = Theme.RED;
    public static boolean renderOverlay = true;
    public static boolean capesEnabled = true;
    public static boolean isSprinting = false;
    private static final boolean isToggleSprintEnabled = false;

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
        if (isMacro) cat = "Auto-Commands";
        KeyBinding kb = new KeyBinding(name, keyCode, cat);
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, kb);
        if (!isMacro) keyBinds.add(kb);
        return kb;
    }

    public void run() {
        AddonManager.start();
        try {
            ConfigManager.start();
            MacroManager.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CURRENT_THEME = Theme.getThemeById(ConfigManager.config.getSelectedTheme());
        addKeyBind("Zoom", ConfigManager.config.getHotkeyZoom(), false);
        addKeyBind("Fulbright", ConfigManager.config.getHotkeyFulbright(), false);
        addKeyBind("Toggle Overlay", ConfigManager.config.getOverlay(), false);
        DISCORD_INSTANCE.start();
        CPS_THREAD_INSTANCE.start();
    }

    public void loop() {
        DISCORD_INSTANCE.loop();
        if (keyBinds.get(0).isKeyDown()) fovModifier = ConfigManager.config.getZoomFactor();
        else fovModifier = 1.0f;
        if (keyBinds.get(1).isPressed()) if (Minecraft.getMinecraft().gameSettings.gammaSetting == 1.0f)
            Minecraft.getMinecraft().gameSettings.gammaSetting = 999999;
        else Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
        if(keyBinds.get(2).isPressed()) renderOverlay = !renderOverlay;
        MacroManager.loop();
        AddonManager.runAddonEvent("loop");
        if(isToggleSprintEnabled) {
            if(Minecraft.getMinecraft().gameSettings.keyBindSprint.isPressed()) isSprinting = !isSprinting;
            if(isSprinting) Minecraft.getMinecraft().thePlayer.setSprinting(true);
        }
    }
}

package org.utilityclient;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import org.apache.commons.lang3.ArrayUtils;
import org.utilityclient.addons.AddonManager;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.crosshair.CrosshairManager;
import org.utilityclient.discord.DiscordRP;
import org.utilityclient.gui.options.overlay.GuiOverlaySettings;
import org.utilityclient.gui.screens.DebugScreen;
import org.utilityclient.overlay.IModule;
import org.utilityclient.overlay.ITheme;
import org.utilityclient.overlay.ModuleHandler;
import org.utilityclient.overlay.modules.*;
import org.utilityclient.overlay.themes.*;
import org.utilityclient.utils.Utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Main Class of UtilityClient.
 * @apiNote Do not create new instances. Instead call {@link UtilityClient#getInstance()}
 * @author Sam302
 * @since 2.0 LTS
 */
public class UtilityClient extends Thread {
    public static final CPSThread CPS_THREAD_INSTANCE = new CPSThread();
    public static final DiscordRP DISCORD_INSTANCE = new DiscordRP();
    private static final String CLIENT_NAME = "Utility Client";
    private static final String CLIENT_VERSION = "3.0-DEV";
    private static final UtilityClient CLIENT_INSTANCE = new UtilityClient();
    public static float fovModifier = 1.0f;
    public static ArrayList<KeyBinding> keyBinds = new ArrayList<>();
    public static ArrayList<ITheme> themes = new ArrayList<>();
    public static int currentTheme = 0;
    public static boolean renderOverlay = true;
    public static boolean isFulbrightEnabled = false;
    public static boolean streamerMode = false;
    public static boolean debugMode = getVersion().endsWith("DEV");
    private static final AddonManager addonManager = new AddonManager();

    /**
     * Use this instead of creating new instances.
     * @return Instance of this class.
     */
    public static UtilityClient getInstance() {
        return CLIENT_INSTANCE;
    }

    /**
     * @return The ID of the UtilityClient Discord Application
     */
    public static long getDiscordApplicationId() {
        return 742760119984455701L;
    }

    /**
     * @return The name of UtilityClient
     */
    public static String getClientName() {
        return CLIENT_NAME;
    }

    /**
     * @return Current UtilityClient Version
     */
    public static String getVersion() {
        return CLIENT_VERSION;
    }

    /**
     * @return If the Overlay should be rendered
     */
    public static boolean shouldRenderOverlay() {
        return renderOverlay;
    }

    /**
     * @return The currently selected theme
     */
    public static ITheme getCurrentTheme() {
        return themes.get(currentTheme);
    }

    /**
     * Register a keybinding
     * @param name Name of the keybinding. Use {@link I18n#translate(String, Object...)} if needed.
     * @param keyCode Currently saved or default keyCode. <a href="https://minecraft.fandom.com/index.php?title=Key_codes/Keyboard1&action=render">List with all key codes<a/>
     * @param isMacro Should be false. This adds the keybinding as a macro.
     * @return The KeyBinding object. Use this to check, if the key is currently pressed.
     */
    public static KeyBinding addKeyBind(String name, int keyCode, boolean isMacro) {
        String cat = CLIENT_NAME;
        if (isMacro) cat = "Macros";
        KeyBinding kb = new KeyBinding(name, keyCode, cat);
        MinecraftClient.getInstance().options.keysAll = ArrayUtils.add(MinecraftClient.getInstance().options.keysAll, kb);
        if (!isMacro) keyBinds.add(kb);
        return kb;
    }

    public void run() {
        Utils.ignore(new File("uc3").mkdirs());
        Utils.ignore(new File("uc3/modules").mkdirs());

        try {
            Config.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyBind(I18n.translate("uc.keybinding.zoom"), Config.getInteger(ConfigEntry.HOTKEY_ZOOM), false);
        addKeyBind(I18n.translate("uc.keybinding.fulbright"), Config.getInteger(ConfigEntry.HOTKEY_FULBRIGHT), false);
        addKeyBind(I18n.translate("uc.keybinding.overlay"), Config.getInteger(ConfigEntry.HOTKEY_OVERLAY), false);
        addKeyBind(I18n.translate("uc.keybinding.copyCoords"), 66, false);
        addKeyBind("Set compass coords", 68, false);
        if(debugMode) addKeyBind("GuiScreen editor", 67, false);

        try {
            CrosshairManager.run();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // TODO: Make this better
        ITheme[] themes1 = {
                new RedTheme(),
                new YellowTheme(),
                new GreenTheme(),
                new BlueTheme(),
                new WhiteTheme(),
                new BlackTheme(),
                new ContrastTheme(),
                new PurpleTheme(),
                new GrayTheme(),
                new AquaTheme(),
                new DaylightCycleTheme()
        };

        themes.addAll(Arrays.asList(themes1));

        // Run Addon Init here
        addonManager.start();

        currentTheme = Config.getInteger(ConfigEntry.SELECTED_THEME);

        IModule[] modules1 = {
                new FPSModule(),
                new CoordsModule(),
                new ClockModule(),
                new DateModule(),
                new FacingModule(),
                new PingModule(),
                new BiomeModule(),
                new DistanceModule()
        };

        ModuleHandler.modules.addAll(Arrays.asList(modules1));

        CPS_THREAD_INSTANCE.start();
        DISCORD_INSTANCE.start();
        try {
            GuiOverlaySettings.loadStates();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        if (keyBinds.size() >= 3) {
            if (keyBinds.get(0).isPressed()) fovModifier = Config.getFloat(ConfigEntry.ZOOM_FACTOR, 0.15f);
            else fovModifier = 1.0f;

            if (keyBinds.get(1).wasPressed()) if (MinecraftClient.getInstance().options.gamma == 1.0f) {
                MinecraftClient.getInstance().options.gamma = 999999;
                isFulbrightEnabled = true;
            } else {
                MinecraftClient.getInstance().options.gamma = 1.0f;
                isFulbrightEnabled = false;
            }

            if(keyBinds.get(2).wasPressed()) renderOverlay = !renderOverlay;

            if(keyBinds.get(3).wasPressed()) {
                String myString = Math.round(MinecraftClient.getInstance().player.x) + " " + Math.round(MinecraftClient.getInstance().player.y) + " " + Math.round(MinecraftClient.getInstance().player.z);
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }

            if(keyBinds.get(4).wasPressed()) {
                try {
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable t = clipboard.getContents(clipboard);
                    if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        String s = (String) t.getTransferData(DataFlavor.stringFlavor);
                        String[] coords = s.split(" ");
                        if(coords.length < 3) throw new IndexOutOfBoundsException();
                        DistanceModule.x = Integer.parseInt(coords[0]);
                        DistanceModule.y = Integer.parseInt(coords[1]);
                        DistanceModule.z = Integer.parseInt(coords[2]);
                        DistanceModule.gotUpdated = true;
                        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(ChatFormatting.GREEN + "Destination updated."));
                    }
                } catch (Exception e) {
                    MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(ChatFormatting.RED + "Error while updating destination."));
                }
            }

            //if(debugMode) if(keyBinds.get(5).wasPressed()) MinecraftClient.getInstance().openScreen(new DebugScreen());
            if(debugMode) if(keyBinds.get(5).wasPressed()) MinecraftClient.getInstance().openScreen(new DebugScreen());
        }
        addonManager.loop();
    }
}

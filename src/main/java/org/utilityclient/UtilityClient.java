package org.utilityclient;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.crosshair.CrosshairManager;
import org.utilityclient.debug.DebugScreen;
import org.utilityclient.discord.DiscordRP;
import org.utilityclient.gui.options.overlay.GuiOverlaySettings;
import org.utilityclient.macro.MacroManager;
import org.utilityclient.overlay.ModuleHandler;
import org.utilityclient.overlay.Theme;
import org.utilityclient.overlay.modules.CPSThread;
import org.utilityclient.overlay.modules.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.utilityclient.utils.Utils;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UtilityClient extends Thread {
    public static final CPSThread CPS_THREAD_INSTANCE = new CPSThread();
    public static final DiscordRP DISCORD_INSTANCE = new DiscordRP();
    private static final String CLIENT_NAME = "Utility Client";
    private static final String CLIENT_VERSION = "2.15-DEV";
    private static final UtilityClient CLIENT_INSTANCE = new UtilityClient();
    public static float fovModifier = 1.0f;
    public static ArrayList<KeyBinding> keyBinds = new ArrayList<>();
    public static Theme CURRENT_THEME = Theme.RED;
    public static boolean renderOverlay = true;
    public static boolean isFulbrightEnabled = false;
    public static boolean streamerMode = false;
    public static boolean debugMode = getVersion().endsWith("DEV");

    public static UtilityClient getInstance() {
        return CLIENT_INSTANCE;
    }
    public static long getDiscordApplicationId() {
        return 742760119984455701L;
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
        Utils.ignore(new File("uc2").mkdirs());
        Utils.ignore(new File("uc2/modules").mkdirs());

        try {
            Config.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyBind(I18n.format("uc.keybinding.zoom"), Config.getInteger(ConfigEntry.HOTKEY_ZOOM), false);
        addKeyBind(I18n.format("uc.keybinding.fulbright"), Config.getInteger(ConfigEntry.HOTKEY_FULBRIGHT), false);
        addKeyBind(I18n.format("uc.keybinding.overlay"), Config.getInteger(ConfigEntry.HOTKEY_OVERLAY), false);
        addKeyBind(I18n.format("uc.keybinding.copyCoords"), 66, false);
        addKeyBind("Set compass coords", 68, false);
        if(debugMode) addKeyBind("GuiScreen editor", 67, false);

        try {
            CrosshairManager.run();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        CURRENT_THEME = Theme.getThemeById(Config.getInteger(ConfigEntry.SELECTED_THEME));
        DISCORD_INSTANCE.start();

        ModuleHandler.modules.add(new FPSModule());
        ModuleHandler.modules.add(new CoordsModule());
        ModuleHandler.modules.add(new ClockModule());
        ModuleHandler.modules.add(new DateModule());
        ModuleHandler.modules.add(new FacingModule());
        ModuleHandler.modules.add(new PingModule());
        ModuleHandler.modules.add(new BiomeModule());
        ModuleHandler.modules.add(new DistanceModule());

        CPS_THREAD_INSTANCE.start();
        try {
            MacroManager.run();
            GuiOverlaySettings.loadStates();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        DISCORD_INSTANCE.loop();
        if (keyBinds.size() >= 3) {
            if (keyBinds.get(0).isKeyDown()) fovModifier = Config.getFloat(ConfigEntry.ZOOM_FACTOR, 0.15f);
            else fovModifier = 1.0f;

            if (keyBinds.get(1).isPressed()) if (Minecraft.getMinecraft().gameSettings.gammaSetting == 1.0f) {
                Minecraft.getMinecraft().gameSettings.gammaSetting = 999999;
                isFulbrightEnabled = true;
            } else {
                Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
                isFulbrightEnabled = false;
            }

            if(keyBinds.get(2).isPressed()) renderOverlay = !renderOverlay;

            if(keyBinds.get(3).isPressed()) {
                String myString = Math.round(Minecraft.getMinecraft().thePlayer.posX) + " " + Math.round(Minecraft.getMinecraft().thePlayer.posY) + " " + Math.round(Minecraft.getMinecraft().thePlayer.posZ);
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }

            if(keyBinds.get(4).isPressed()) {
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
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Destination updated."));
                    }
                } catch (Exception e) {
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Error while updating destination."));
                }
            }

            if(debugMode) if(keyBinds.get(5).isPressed()) Minecraft.getMinecraft().displayGuiScreen(new DebugScreen());
        }
        MacroManager.loop();
    }
}

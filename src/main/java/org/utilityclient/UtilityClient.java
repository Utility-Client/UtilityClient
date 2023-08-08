package org.utilityclient;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.input.Keyboard;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.utilityclient.api.*;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.crosshair.CrosshairManager;
import org.utilityclient.discord.DiscordRP;
import org.utilityclient.gui.options.overlay.GuiOverlaySettings;
import org.utilityclient.overlay.modules.*;
import org.utilityclient.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * The Main Class of UtilityClient.
 *
 * @author Sam302
 * @apiNote Do not create new instances. Instead call {@link UtilityClient#getInstance()}
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
    public static ArrayList<Theme> themes = new ArrayList<>();
    public static ArrayList<Macro> macros = new ArrayList<>();
    public static int currentTheme = 0;
    public static boolean renderOverlay = true;
    public boolean isFulbrightEnabled = false;
    public static boolean streamerMode = false;
    public static boolean debugMode = getVersion().endsWith("DEV");
    public static ArrayList<String> packageSearchIndex = new ArrayList<>();

    /**
     * Use this instead of creating new instances.
     *
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
    public static Theme getCurrentTheme() {
        return themes.get(currentTheme);
    }

    public void run() {
        Utils.ignore(new File("uc3").mkdirs());
        Utils.ignore(new File("uc3/modules").mkdirs());

        try {
            Config.run();
            macros.addAll(Macro.loadAllMacros());
            CrosshairManager.run();
            GuiOverlaySettings.loadStates();
        } catch (Exception e) {
            e.printStackTrace();
        }

        packageSearchIndex.addAll(Arrays.asList(
                "org.utilityclient.overlay.themes",
                "org.utilityclient.overlay.modules",
                "org.utilityclient.keybindings"
        ));

        Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages(packageSearchIndex.toArray(new String[] {})));
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Register.class);

        for (Class<?> clazz : annotated) {
            try {
                Object instance = clazz.getDeclaredConstructors()[0].newInstance();
                if (instance instanceof Registrable) {
                    Registrable r = (Registrable) instance;
                    r.register();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        currentTheme = Config.getInteger(ConfigEntry.SELECTED_THEME);
        CPS_THREAD_INSTANCE.start();
        DISCORD_INSTANCE.start();
    }

    public void loop() {
        for (KeyBinding kb : keyBinds) kb.frame();

        for (Macro macro : macros) {
            if (macro.state && !Keyboard.isKeyDown(macro.KeyCode))
                MinecraftClient.getInstance().player.sendChatMessage(macro.Message);
            macro.state = Keyboard.isKeyDown(macro.KeyCode);
        }
    }
}
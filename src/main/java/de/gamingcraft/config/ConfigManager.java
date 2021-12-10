package de.gamingcraft.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final String configFileName = "_utilityclient.conf";
    private static final File configFile = new File(configFileName);
    public static Config config;
    private static final Properties conf = new Properties();

    public static void start() throws IOException {
        setup();
        if (!configFile.exists()) save();
        load();
        overrideConfig(config.getSelectedTheme(), config.getHotkeyZoom(), config.getHotkeyFulbright(), config.getCrosshair(), config.getCrosshairSize(), config.getOverlay(), config.getZoomFactor());
    }

    public static void setup() {
        conf.setProperty("selectedTheme", "0");
        conf.setProperty("hotkeyZoom", "46");
        conf.setProperty("hotkeyFulbright", "50");
        conf.setProperty("crosshairData", "0");
        conf.setProperty("crosshairSize", "9");
        conf.setProperty("hotkeyToggleOverlay", "22");
        conf.setProperty("zoomFactor", String.valueOf(0.15f));
    }

    public static void overrideConfig(int selTheme, int hotKeyZoom, int hotkeyFulbright, String crosshair, int crosshairSize, int overlay, float zoomFactor) throws IOException {
        setEntry("selectedTheme", selTheme);
        setEntry("hotkeyZoom", hotKeyZoom);
        setEntry("hotkeyFulbright", hotkeyFulbright);
        setEntry("crosshairData", crosshair);
        setEntry("crosshairSize", crosshairSize);
        setEntry("hotkeyToggleOverlay", overlay);
        setEntry("zoomFactor", String.valueOf(zoomFactor));
        save();
        load();
    }

    public static void setEntry(String key, int value) throws IOException {
        setEntry(key, value + "");
    }

    public static void setEntry(String key, String value) throws IOException {
        conf.setProperty(key, value);
        save();
        load();
    }

    public static void load() throws IOException {
        conf.load(new FileInputStream(configFileName));

        config = new Config(
                Integer.parseInt(conf.getProperty("selectedTheme")),
                Integer.parseInt(conf.getProperty("hotkeyZoom")),
                Integer.parseInt(conf.getProperty("hotkeyFulbright")),
                conf.getProperty("crosshairData"),
                Integer.parseInt(conf.getProperty("crosshairSize")),
                Integer.parseInt(conf.getProperty("hotkeyToggleOverlay")),
                Float.parseFloat(conf.getProperty("zoomFactor"))
        );
    }

    public static void save() throws IOException {
        conf.store(new FileOutputStream(configFileName), configFileName);
    }
}

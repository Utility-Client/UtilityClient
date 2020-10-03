package de.gamingcraft.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties conf = new Properties();
    public static Config config;
    private static final String configFileName = "_utilityclient.conf";
    private static final File configFile = new File(configFileName);

    public static void start() throws IOException {
        setup();
        if(!configFile.exists()) save();
        load();
        overrideConfig(config.getSelectedTheme(), config.getHotkeyZoom(), config.getHotkeyFulbright(), config.getCrosshair());
    }

    public static void setup() {
        conf.setProperty("selectedTheme", "0");
        conf.setProperty("hotkeyZoom", "46");
        conf.setProperty("hotkeyFulbright", "50");
        conf.setProperty("selectedCrosshair", "0");
    }

    public static void overrideConfig(int selTheme, int hotKeyZoom, int hotkeyFulbright, int crosshair) throws IOException {
        conf.setProperty("selectedTheme", selTheme+"");
        conf.setProperty("hotkeyZoom", hotKeyZoom+"");
        conf.setProperty("hotkeyFulbright", hotkeyFulbright+"");
        conf.setProperty("selectedCrosshair", crosshair+"");
        save();
        load();
    }

    public static void load() throws IOException {
        conf.load(new FileInputStream(configFileName));

        config = new Config(
                Integer.parseInt(conf.getProperty("selectedTheme")),
                Integer.parseInt(conf.getProperty("hotkeyZoom")),
                Integer.parseInt(conf.getProperty("hotkeyFulbright")),
                Integer.parseInt(conf.getProperty("selectedCrosshair"))
        );
    }

    public static void save() throws IOException {
        conf.store(new FileOutputStream(configFileName), configFileName);
    }
}

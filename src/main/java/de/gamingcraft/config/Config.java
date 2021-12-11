package de.gamingcraft.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Config {
    private static HashMap<String, String> config = new HashMap<>();
    private static File configFile = new File("uc2/config.cfg");

    public static void run() throws IOException {
        config.clear();
        if(!configFile.createNewFile()) load();
    }

    private static void load() throws FileNotFoundException {
        Scanner scanner = new Scanner(configFile);
        while(scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split("=");
            if(split.length == 2) config.put(split[0], split[1]);
        }
        // Proper disposal 🚽
        scanner.close();
    }

    public static void save() throws IOException {
        assert configFile.delete();
        assert configFile.createNewFile();
        FileWriter fw = new FileWriter(configFile, false);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> ce : config.entrySet()) sb.append(ce.getKey()).append("=").append(ce.getValue()).append("\n");
        fw.write(sb.toString());
        // Proper disposal 🚽
        fw.flush();
        fw.close();
    }

    public static HashMap<String, String> getConfig() {
        return config;
    }

    public static int getInteger(String key, int defaultValue) {
        return Integer.parseInt(getConfig().getOrDefault(key, String.valueOf(defaultValue)));
    }

    public static int getInteger(ConfigEntry key, int defaultValue) {
        return Integer.parseInt(getConfig().getOrDefault(key.getKey(), String.valueOf(defaultValue)));
    }

    public static String getString(String key, String defaultValue) {
        return getConfig().getOrDefault(key, defaultValue);
    }

    public static String getString(ConfigEntry key, String defaultValue) {
        return getConfig().getOrDefault(key.getKey(), defaultValue);
    }

    public static float getFloat(String key, float defaultValue) {
        return Float.parseFloat(getConfig().getOrDefault(key, String.valueOf(defaultValue)));
    }

    public static float getFloat(ConfigEntry key, float defaultValue) {
        return Float.parseFloat(getConfig().getOrDefault(key.getKey(), String.valueOf(defaultValue)));
    }

    public static void setInteger(String key, int value) {
        getConfig().put(key, String.valueOf(value));
    }

    public static void setInteger(ConfigEntry key, int value) {
        getConfig().put(key.getKey(), String.valueOf(value));
    }

    public static void setString(String key, String value) {
        getConfig().put(key, String.valueOf(value));
    }

    public static void setString(ConfigEntry key, String value) {
        getConfig().put(key.getKey(), String.valueOf(value));
    }

    public static void setFloat(String key, float value) {
        getConfig().put(key, String.valueOf(value));
    }

    public static void setFloat(ConfigEntry key, float value) {
        getConfig().put(key.getKey(), String.valueOf(value));
    }

    // TODO Needed config entries
    // - selectedTheme: int
    // - hotkeyZoom: int
    // - hotkeyFulbright: int
    // - overlay: int
    // - crosshairSize: int
    // - crosshairData: string
    // - zoomFactor: float
}

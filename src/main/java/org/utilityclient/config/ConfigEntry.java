package org.utilityclient.config;

public enum ConfigEntry {
    SELECTED_THEME("selectedTheme", "0"),
    HOTKEY_ZOOM("hotkeyZoom", "46"),
    HOTKEY_FULBRIGHT("hotkeyFulbright", "50"),
    HOTKEY_OVERLAY("hotkeyOverlay", "22"),
    CROSSHAIR_SIZE("crosshairSize", "11"),
    ZOOM_FACTOR("zoomFactor", String.valueOf(0.15f)),
    KEYSTROKES("keystrokesEnabled", "true"),
    TOGGLE_SPRINT("toggleSprintEnabled", "false"),
    OVERLAY_BACKGROUND("overlayBackgroundEnabled", "true");

    private final String key;
    private final String defaultValue;

    ConfigEntry(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return key;
    }
}

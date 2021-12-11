package de.gamingcraft.config;

public enum ConfigEntry {
    SELECTED_THEME("selectedTheme"),
    HOTKEY_ZOOM("hotkeyZoom"),
    HOTKEY_FULBRIGHT("hotkeyFulbright"),
    HOTKEY_OVERLAY("hotkeyOverlay"),
    CROSSHAIR_SIZE("crosshairSize"),
    CROSSHAIR_DATA("crosshairData"),
    ZOOM_FACTOR("zoomFactor");

    String key;

    ConfigEntry(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return key;
    }
}

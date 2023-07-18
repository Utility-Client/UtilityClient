package org.utilityclient.config;

/**
 * Config Entries with default values in Enum-form
 * @since 2.12
 * @author GamingCraft
 */
public enum ConfigEntry {
    SELECTED_THEME("selectedTheme", "0"),
    HOTKEY_ZOOM("hotkeyZoom", "46"),
    HOTKEY_FULBRIGHT("hotkeyFulbright", "50"),
    HOTKEY_OVERLAY("hotkeyOverlay", "22"),
    CROSSHAIR_SIZE("crosshairSize", "11"),
    ZOOM_FACTOR("zoomFactor", String.valueOf(0.15f)),
    KEYSTROKES("keystrokesEnabled", "true"),
    TOGGLE_SPRINT("toggleSprintEnabled", "false"),
    OVERLAY_BACKGROUND("overlayBackgroundEnabled", "true"),
    DISCORD_RICH_PRESENCE("discordRichPresenceEnabled", "true"),
    DISCORD_FRIEND_NOTIFICATIONS("discordFriendNotifications", "true"),
    DISCORD_SHOW_SERVER("discordShowServerInPresence", "true"),
    CROSSHAIR_COLOR("crosshairColor", String.valueOf(2164260863L)),
    DATE_PATTERN("datePattern", "dd/MM/YYYY"),
    TIME_PATTERN("timePattern", "HH:mm:ss"),
    RENDER_RAIN_SNOW("renderRainSnow", "true");

    private final String key;
    private final String defaultValue;

    ConfigEntry(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    /**
     * @return The key, as which the entry got saved as.
     */
    public String getKey() {
        return key;
    }

    /**
     * @return The default value, if the key didn't exist yet.
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @return The value of {@link #getKey()}
     * @see #getKey()
     */
    @Override
    public String toString() {
        return getKey();
    }
}
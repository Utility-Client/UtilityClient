package org.utilityclient.config;

/**
 * Config Entries with default values as enum
 * @author Sam302
 * @since 2.12
 */
public enum ConfigEntry {
    SELECTED_THEME("overlay.theme", "0"),
    OVERLAY_BACKGROUND("overlay.background", "true"),
    ZOOM_FACTOR("zoom.factor", "0.15"),
    DISCORD_RICH_PRESENCE("discord.presence.active", "true"),
    DISCORD_FRIEND_NOTIFICATIONS("discord.friends.active", "true"),
    DISCORD_SHOW_SERVER("discord.presence.show_server", "true"),
    CROSSHAIR_SIZE("crosshair.size", "11"),
    CROSSHAIR_COLOR("crosshair.color", "2164260863"),
    DATE_PATTERN("pattern.date", "dd/MM/YYYY"),
    TIME_PATTERN("pattern.time", "HH:mm:ss"),
    KEYSTROKES("keystrokes.active", "true"),
    SPRINT_AND_CROUCH_KEYSTROKES("keystrokes.sprint_and_crouch", "false"),
    SHOW_CHANGELOG_IN_TITLE_SCREEN("changelog.active", "true");

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
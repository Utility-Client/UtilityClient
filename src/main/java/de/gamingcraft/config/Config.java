package de.gamingcraft.config;

public class Config {
    private int selectedTheme, hotkeyZoom, hotkeyFulbright;

    public Config(int _selectedTheme, int _hotkeyZoom, int _hotkeyFulbright) {
        this.selectedTheme = _selectedTheme;
        this.hotkeyZoom = _hotkeyZoom;
        this.hotkeyFulbright = _hotkeyFulbright;
    }

    public int getSelectedTheme() {
        return selectedTheme;
    }

    public int getHotkeyZoom() {
        return hotkeyZoom;
    }

    public int getHotkeyFulbright() {
        return hotkeyFulbright;
    }
}

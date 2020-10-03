package de.gamingcraft.config;

public class Config {
    private int selectedTheme, hotkeyZoom, hotkeyFulbright, crosshair;

    public Config(int _selectedTheme, int _hotkeyZoom, int _hotkeyFulbright, int _crosshair) {
        this.selectedTheme = _selectedTheme;
        this.hotkeyZoom = _hotkeyZoom;
        this.hotkeyFulbright = _hotkeyFulbright;
        this.crosshair = _crosshair;
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

    public int getCrosshair() {
        return crosshair;
    }

    public void setCrosshair(int crosshair) {
        this.crosshair = crosshair;
    }

    public void setHotkeyFulbright(int hotkeyFulbright) {
        this.hotkeyFulbright = hotkeyFulbright;
    }

    public void setHotkeyZoom(int hotkeyZoom) {
        this.hotkeyZoom = hotkeyZoom;
    }

    public void setSelectedTheme(int selectedTheme) {
        this.selectedTheme = selectedTheme;
    }
}

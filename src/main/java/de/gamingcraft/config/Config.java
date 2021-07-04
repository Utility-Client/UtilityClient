package de.gamingcraft.config;

public class Config {
    private int selectedTheme, hotkeyZoom, hotkeyFulbright, crosshair, overlay;

    public Config(int _selectedTheme, int _hotkeyZoom, int _hotkeyFulbright, int _crosshair, int _overlay) {
        selectedTheme = _selectedTheme;
        hotkeyZoom = _hotkeyZoom;
        hotkeyFulbright = _hotkeyFulbright;
        crosshair = _crosshair;
        overlay = _overlay;
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

    public int getOverlay() {
        return overlay;
    }

    public void setOverlay(int overlay) {
        this.overlay = overlay;
    }
}

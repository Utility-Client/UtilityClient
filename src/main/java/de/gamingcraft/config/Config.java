package de.gamingcraft.config;

public class Config {
    private int selectedTheme, hotkeyZoom, hotkeyFulbright, overlay, crosshairSize;
    private String crosshair;
    private float zoomFactor;

    public Config(int _selectedTheme, int _hotkeyZoom, int _hotkeyFulbright, String _crosshair, int _crosshairSize, int _overlay, float _zoomFactor) {
        selectedTheme = _selectedTheme;
        hotkeyZoom = _hotkeyZoom;
        hotkeyFulbright = _hotkeyFulbright;
        crosshair = _crosshair;
        overlay = _overlay;
        crosshairSize = _crosshairSize;
        zoomFactor = _zoomFactor;
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

    public String getCrosshair() {
        return crosshair;
    }

    public int getCrosshairSize() {
        return crosshairSize;
    }

    public void setCrosshairSize(int crosshairSize) {
        this.crosshairSize = crosshairSize;
    }

    public void setCrosshair(String crosshair) {
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

    public float getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(float zoomFactor) {
        this.zoomFactor = zoomFactor;
    }
}

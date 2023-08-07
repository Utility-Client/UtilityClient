package org.utilityclient.gui.components;

import net.minecraft.client.MinecraftClient;

/**
 * The base interface for all GUI components of the UC3 GUI Library.
 * @author Sam302
 * @since 3.0
 */
public abstract class GuiComponent {
    private final MinecraftClient client = MinecraftClient.getInstance();
    public final int x, y, width, height;

    public GuiComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render(int mouseX, int mouseY, float tickDelta);

    public abstract void mouseClicked(int mouseX, int mouseY);

    public boolean contains(int x, int y) {
        return (x >= this.x && x <= this.x + this.width) && (y >= this.y && y <= this.y + this.height);
    }
}

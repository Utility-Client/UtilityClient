package org.utilityclient.gui.components;

import net.minecraft.client.MinecraftClient;

/**
 * The base interface for all GUI components of the UC3 GUI Library.
 * @author Sam302
 * @since 3.0
 */
public abstract class GuiComponent {
    /**
     * More compact access to the Minecraft client main-class.
     * Of course, MinecraftClient.getInstance() also works.
     * @see MinecraftClient
     */
    protected final MinecraftClient client = MinecraftClient.getInstance();

    /**
     * The position from the point of the parent.
     * @apiNote Can be mostly disregarded.
     */
    public final int localX, localY;

    /**
     * Dimensions of the component.
     */
    public final int width, height;

    /**
     * The position from the point of the screen.
     * @apiNote Use for rendering.
     */
    public int screenX, screenY;

    public GuiComponent parent;

    public GuiComponent(int x, int y, int width, int height) {
        this.localX = x; this.screenX = x;
        this.localY = y; this.screenY = y;
        this.width = width;
        this.height = height;
    }

    public GuiComponent(int x, int y, int width, int height, GuiComponent parent) {
        this.localX = x;
        this.localY = y;
        this.width = width;
        this.height = height;
        setParent(parent);
    }

    public void setParent(GuiComponent p) {
        parent = p;
        screenX = p.screenX + localX;
        screenY = p.screenY + localY;
    }

    public abstract void render(int mouseX, int mouseY, float tickDelta);

    public abstract void mouseClicked(int mouseX, int mouseY);

    /**
     * Checks if the point (screen-based coordinates) is inside the bounding box of this GuiComponent.
     * @param x X-coordinate to check. (screen-based)
     * @param y Y-coordinate to check. (screen-based)
     * @return True if x and y are in-bounds.
     */
    public boolean contains(int x, int y) {
        return (x >= this.screenX && x <= this.screenX + this.width) && (y >= this.screenY && y <= this.screenY + this.height);
    }
}

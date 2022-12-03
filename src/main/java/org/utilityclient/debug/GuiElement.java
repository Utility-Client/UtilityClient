package org.utilityclient.debug;

import org.utilityclient.Instances;

public abstract class GuiElement extends Instances {
    private final int id;
    private final String text;
    private final int x;
    private final int y;
    private final int width;

    public GuiElement(int id, String text, int x, int y, int width) {
        this.id = id;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public abstract void drawScreen(int mouseX, int mouseY, float partialTicks);

    public void keyTyped(char typedChar, int keyCode) {

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }
}

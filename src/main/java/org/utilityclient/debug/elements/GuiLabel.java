package org.utilityclient.debug.elements;

import org.utilityclient.debug.GuiElement;

public class GuiLabel extends GuiElement {
    public GuiLabel(int id, String text, int x, int y, int width) {
        super(id, text, x, y, width);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc().textRenderer.draw(getText(), getX(), getY(), 0);
    }
}

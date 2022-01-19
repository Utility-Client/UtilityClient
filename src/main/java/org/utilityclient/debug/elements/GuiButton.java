package org.utilityclient.debug.elements;

import org.utilityclient.debug.GuiElement;

public class GuiButton extends GuiElement {
    net.minecraft.client.gui.GuiButton btn;

    public GuiButton(int id, String text, int x, int y, int width) {
        super(id, text, x, y, width);
        btn = new net.minecraft.client.gui.GuiButton(id, x, y, width, 20, text);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        btn.drawButton(mc(), mouseX, mouseY);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        btn.mousePressed(mc(), mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        btn.mouseReleased(mouseX, mouseY);
    }
}

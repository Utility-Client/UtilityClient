package org.utilityclient.debug.elements;

import net.minecraft.client.gui.widget.ButtonWidget;
import org.utilityclient.debug.GuiElement;

public class GuiButton extends GuiElement {
    ButtonWidget btn;

    public GuiButton(int id, String text, int x, int y, int width) {
        super(id, text, x, y, width);
        btn = new ButtonWidget(id, x, y, width, 20, text);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        btn.render(mc(), mouseX, mouseY);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        btn.isMouseOver(mc(), mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        btn.mouseReleased(mouseX, mouseY);
    }
}

package org.utilityclient.debug.elements;

import net.minecraft.client.gui.widget.TextFieldWidget;
import org.utilityclient.debug.GuiElement;

public class GuiInput extends GuiElement {
    TextFieldWidget textField;

    public GuiInput(int id, String text, int x, int y, int width) {
        super(id, text, x, y, width);
        textField = new TextFieldWidget(id, mc().textRenderer, x, y, width, 20);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        textField.render();
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        textField.keyPressed(typedChar, keyCode);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        textField.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
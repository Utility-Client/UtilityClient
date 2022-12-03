package org.utilityclient.debug.elements;

import net.minecraft.client.gui.GuiTextField;
import org.utilityclient.debug.GuiElement;

public class GuiInput extends GuiElement {
    GuiTextField textField;

    public GuiInput(int id, String text, int x, int y, int width) {
        super(id, text, x, y, width);
        textField = new GuiTextField(id, mc().fontRendererObj, x, y, width, 20);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        textField.drawTextBox();
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        textField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        textField.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
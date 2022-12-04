package org.utilityclient.debug;

import net.minecraft.client.gui.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;

import static org.utilityclient.debug.GuiScreenParser.gsp;

public class DebugScreen extends Screen {
    private final DebugFrame debugFrame;
    private ArrayList<GuiElement> elements = new ArrayList<>();

    public DebugScreen() {
        debugFrame = new DebugFrame(this);
        reload();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawDefaultBackground();
        elements.forEach(e -> e.drawScreen(mouseX, mouseY, partialTicks));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        elements.forEach(e -> e.keyTyped(typedChar, keyCode));
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        elements.forEach(e -> e.mouseClicked(mouseX, mouseY, mouseButton));
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        elements.forEach(e -> e.mouseReleased(mouseX, mouseY, state));
    }

    public void reload() {
        try {
            elements = gsp().parse(gsp().readFile());
            debugFrame.setTitle("Reload successful!");
        } catch (Exception e) {
            debugFrame.setTitle("Error while reloading!");
            e.printStackTrace();
        }
    }
}

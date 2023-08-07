package org.utilityclient.gui.screens;

import com.google.common.collect.Lists;
import org.utilityclient.gui.components.GuiContainer;
import org.utilityclient.gui.components.GuiHyperlink;
import org.utilityclient.gui.components.GuiLabel;
import org.utilityclient.gui.components.GuiScreen;
import org.utilityclient.utils.Color;

public class DebugScreen extends GuiScreen {
    public DebugScreen() {
        super("Testing GUI features");
        shouldRenderBackground = true;
        add(new GuiContainer(2, 2, 512, 512, Lists.newArrayList(
            new GuiLabel("Hello world <3", 2, 2, Color.TEXT.color, false),
            new GuiHyperlink("Click me", () -> System.out.println("You clicked me!"), 2, 12, Color.TEXT.color, false)
        )));
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        super.render(mouseX, mouseY, tickDelta);
    }
}

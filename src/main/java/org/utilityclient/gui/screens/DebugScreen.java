package org.utilityclient.gui.screens;

import com.google.common.collect.Lists;
import org.utilityclient.gui.components.GuiContainer;
import org.utilityclient.gui.components.GuiScreen;

public class DebugScreen extends GuiScreen {
    public DebugScreen() {
        super("Testing GUI features");
        shouldRenderBackground = true;
        add(new GuiContainer(2, 2, 512, 512, Lists.newArrayList()));
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        super.render(mouseX, mouseY, tickDelta);
    }
}

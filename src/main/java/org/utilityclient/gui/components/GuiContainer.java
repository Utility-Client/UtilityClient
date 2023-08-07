package org.utilityclient.gui.components;

import org.utilityclient.utils.Color;
import org.utilityclient.utils.RenderHelper;

import java.util.List;

public class GuiContainer extends GuiComponent {
    public List<GuiComponent> children;

    public GuiContainer(int x, int y, int width, int height, List<GuiComponent> children) {
        super(x, y, width, height);
        this.children = children;
        for (GuiComponent c : children) c.setParent(this);
    }

    public GuiContainer(int x, int y, int width, int height, GuiComponent parent, List<GuiComponent> children) {
        super(x, y, width, height, parent);
        this.children = children;
        for (GuiComponent c : children) c.setParent(this);
    }

    public void add(GuiComponent c) {
        c.setParent(this);
        children.add(c);
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        RenderHelper.rect(screenX, screenY, width, height, Color.BACKGROUND.color, 1f, true);
        for (GuiComponent c : children) c.render(mouseX, mouseY, tickDelta);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        for (GuiComponent c : children) if (c.contains(mouseX, mouseY)) c.mouseClicked(mouseX, mouseY);
    }
}

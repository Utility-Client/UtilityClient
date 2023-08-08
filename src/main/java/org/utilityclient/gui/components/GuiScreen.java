package org.utilityclient.gui.components;

import net.minecraft.client.gui.screen.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam302
 * @since 3.0
 */
public class GuiScreen extends Screen {
    /**
     * @see GuiScreen#getActionLabel()
     */
    private final String actionLabel;
    private final List<GuiComponent> components = new ArrayList<>();
    protected boolean shouldRenderBackground = false;
    protected Screen parent;

    public GuiScreen(String actionLabel) {
        super();
        this.actionLabel = actionLabel;
    }

    public GuiScreen(String actionLabel, Screen parent) {
        this(actionLabel);
        this.parent = parent;
    }

    /**
     * @return What the player is currently doing. Override if dynamic
     * @since 3.0
     */
    public String getActionLabel() {
        return actionLabel;
    }

    @Override
    public void init() {
        super.init();
        components.clear();
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        if (shouldRenderBackground) renderBackground();
        super.render(mouseX, mouseY, tickDelta);
        components.forEach(c -> c.render(mouseX, mouseY, tickDelta));
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        components.forEach(c -> {
            if(c.contains(mouseX, mouseY)) c.mouseClicked(mouseX, mouseY);
        });
    }

    public void add(GuiComponent c) {
        components.add(c);
    }
}

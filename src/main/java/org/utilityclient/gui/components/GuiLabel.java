package org.utilityclient.gui.components;

import net.minecraft.client.MinecraftClient;

public class GuiLabel extends GuiComponent {
    protected final String text;
    protected final int color;
    protected final boolean withShadow;

    public GuiLabel(String text, int x, int y, int color, boolean withShadow) {
        super(x, y, MinecraftClient.getInstance().textRenderer.getStringWidth(text), 10);
        this.text = text;
        this.color = color;
        this.withShadow = withShadow;
    }

    public GuiLabel(String text, int x, int y, int color, boolean withShadow, GuiComponent parent) {
        super(x, y, MinecraftClient.getInstance().textRenderer.getStringWidth(text), 10, parent);
        this.text = text;
        this.color = color;
        this.withShadow = withShadow;
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        client.textRenderer.draw(text, screenX, screenY, color, withShadow);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {

    }
}

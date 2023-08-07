package org.utilityclient.gui.components;

import com.mojang.realmsclient.gui.ChatFormatting;

public class GuiHeader extends GuiLabel {
    public GuiHeader(String text, int x, int y, int color, boolean withShadow) {
        super(text, x, y, color, withShadow);
    }

    public GuiHeader(String text, int x, int y, int color, boolean withShadow, GuiComponent parent) {
        super(text, x, y, color, withShadow, parent);
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        client.textRenderer.draw(ChatFormatting.BOLD + text, screenX, screenY, color, withShadow);
    }
}

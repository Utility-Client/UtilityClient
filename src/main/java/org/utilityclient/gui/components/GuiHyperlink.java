package org.utilityclient.gui.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.util.Identifier;
import org.utilityclient.utils.callback.VoidCallback;

public class GuiHyperlink extends GuiLabel {
    private final VoidCallback cb;

    public GuiHyperlink(String text, VoidCallback cb, int x, int y, int color, boolean withShadow) {
        super(text, x, y, color, withShadow);
        this.cb = cb;
    }

    public GuiHyperlink(String text, VoidCallback cb, int x, int y, int color, boolean withShadow, GuiComponent parent) {
        super(text, x, y, color, withShadow, parent);
        this.cb = cb;
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        boolean isHovering = contains(mouseX, mouseY);
        client.textRenderer.draw((isHovering ? ChatFormatting.UNDERLINE : "") + text, screenX, screenY, color, withShadow);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        super.mouseClicked(mouseX, mouseY);
        if (contains(mouseX, mouseY)) {
            client.getSoundManager().play(PositionedSoundInstance.master(new Identifier("gui.button.press"), 1.0F));
            cb.callback();
        }
    }
}

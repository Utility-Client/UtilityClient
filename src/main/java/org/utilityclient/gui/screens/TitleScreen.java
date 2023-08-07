package org.utilityclient.gui.screens;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.DrawableHelper;
import org.utilityclient.gui.GuiHelper;
import org.utilityclient.gui.components.GuiScreen;
import org.utilityclient.gui.components.GuiUCLogo;
import org.utilityclient.gui.overrides.GuiMainMenu;
import org.utilityclient.utils.MathUtil;
import org.utilityclient.utils.Utils;

import java.time.LocalDateTime;

public class TitleScreen extends GuiScreen {
    public TitleScreen() {
        super("On the title screen");
    }

    @Override
    public void init() {
        super.init();
        GuiUCLogo logo = new GuiUCLogo(width / 2, height / 5, .35f, GuiUCLogo.Variant.UC3, true);
        add(logo);
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        drawUtilityClientBackground();
        super.render(mouseX, mouseY, tickDelta);
    }

    public void drawUtilityClientBackground() {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        client.getTextureManager().bindTexture(Utils.getSeasonOfMonth(LocalDateTime.now().getMonthValue()).getIdentifier());
        DrawableHelper.drawTexture(0, 0, 0, 0, width, height, width, height, width, height);
    }
}

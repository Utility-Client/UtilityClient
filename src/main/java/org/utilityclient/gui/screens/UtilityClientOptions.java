package org.utilityclient.gui.screens;

import net.minecraft.client.gui.screen.Screen;
import org.utilityclient.gui.components.GuiContainer;
import org.utilityclient.gui.components.GuiHeader;
import org.utilityclient.gui.components.GuiScreen;
import org.utilityclient.gui.components.GuiUCLogo;
import org.utilityclient.utils.RenderHelper;
import java.util.Arrays;

public class UtilityClientOptions extends GuiScreen {
    private GuiUCLogo logo;

    public UtilityClientOptions(Screen parent) {
        super("Configuring UtilityClient...", parent);
        shouldRenderBackground = true;

    }

    @Override
    public void init() {
        super.init();
        logo = new GuiUCLogo(32, 32, .5f, GuiUCLogo.Variant.UC, false);
        add(logo);
        add(new GuiHeader("Settings", logo.screenX + logo.width + 31, logo.screenY + 15, -1, false));

        int dim = width/24;

        add(new GuiContainer(dim, logo.height + 64, 6*dim, height - logo.height - logo.screenY*2 - 40, Arrays.asList()));
        add(new GuiContainer(dim*8, logo.height + 64, 15*dim, height - logo.height - logo.screenY*2 - 40, Arrays.asList()));
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        super.render(mouseX, mouseY, tickDelta);
        RenderHelper.rect(logo.screenX + logo.width + 15, logo.screenY + 8, 1, logo.height - 16, -1, 1, false);
    }
}

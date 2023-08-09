package org.utilityclient.gui.screens;

import org.utilityclient.UtilityClient;
import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.gui.components.GuiScreen;
import org.utilityclient.gui.components.GuiUCLogo;
import org.utilityclient.utils.Season;

import java.time.LocalDateTime;

@StandaloneCompatible
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
        UtilityClient.getInstance().wrapper.drawUtilityClientBackground(width, height, Season.getSeasonOfMonth(LocalDateTime.now().getMonthValue()).getIdentifier());
        super.render(mouseX, mouseY, tickDelta);
    }
}

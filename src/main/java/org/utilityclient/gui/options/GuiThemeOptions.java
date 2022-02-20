package org.utilityclient.gui.options;

import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.utilityclient.utils.Color;

import java.io.IOException;

public class GuiThemeOptions extends GuiScreen {
    private final GuiScreen parentScreen;
    private String title;

    public GuiThemeOptions(GuiScreen parentScreenIn) {
        this.parentScreen = parentScreenIn;
    }

    public void initGui() {
        this.title = I18n.format("uc.options.theme.title");

        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 - 40, 100, 20, I18n.format("uc.options.theme.previous")));
        this.buttonList.add(new GuiButton(2, this.width / 2, this.height / 2 - 40, 100, 20, I18n.format("uc.options.theme.next")));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 2, I18n.format("gui.done")));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button.id == 1) {
                UtilityClient.currentTheme--;
                if(UtilityClient.currentTheme == -1) UtilityClient.currentTheme = UtilityClient.themes.size() - 1;
            }

            if (button.id == 2) {
                UtilityClient.currentTheme++;
                if(UtilityClient.currentTheme == UtilityClient.themes.size()) UtilityClient.currentTheme = 0;
            }

            if (button.id == 200) {
                Config.setInteger(ConfigEntry.SELECTED_THEME, UtilityClient.currentTheme);
                Config.save();
                this.mc.displayGuiScreen(this.parentScreen);
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, Color.TEXT.color);
        this.drawCenteredString(this.fontRendererObj, "Current theme: " + UtilityClient.getCurrentTheme().getName(), this.width / 2, this.height / 2 - 90, Color.TEXT.color);
        this.drawCenteredString(this.fontRendererObj,
                UtilityClient.getCurrentTheme().getPrefixColor() + I18n.format("uc.options.theme.prefix")
                        + UtilityClient.getCurrentTheme().getSeparator()
                        + UtilityClient.getCurrentTheme().getSuffixColor() +  I18n.format("uc.options.theme.suffix"),
                this.width / 2, this.height / 2 - 70, Color.TEXT.color);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

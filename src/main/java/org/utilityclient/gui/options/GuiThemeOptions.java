package org.utilityclient.gui.options;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.gui.components.GuiScreen;
import org.utilityclient.utils.Color;

public class GuiThemeOptions extends GuiScreen {
    private final Screen parentScreen;
    private String title;

    public GuiThemeOptions(Screen parentScreenIn) {
        super("Choosing a color scheme");
        this.parentScreen = parentScreenIn;
    }

    public void init() {
        this.title = I18n.translate("uc.options.theme.title");

        buttons.add(new ButtonWidget(1, this.width / 2 - 100, this.height / 2 - 40, 100, 20, I18n.translate("uc.options.theme.previous")));
        buttons.add(new ButtonWidget(2, this.width / 2, this.height / 2 - 40, 100, 20, I18n.translate("uc.options.theme.next")));
        buttons.add(new ButtonWidget(200, this.width / 2 - 100, this.height / 2, I18n.translate("gui.done")));
    }

    protected void buttonClicked(ButtonWidget button) {
        if (button.active) {
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
                try {
                    Config.save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                client.openScreen(this.parentScreen);
            }
        }
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        drawCenteredString(textRenderer, this.title, this.width / 2, 20, Color.TEXT.color);
        drawCenteredString(textRenderer, "Current theme: " + UtilityClient.getCurrentTheme().getName(), this.width / 2, this.height / 2 - 90, Color.TEXT.color);
        drawCenteredString(textRenderer,
                UtilityClient.getCurrentTheme().getPrefixColor() + I18n.translate("uc.options.theme.prefix")
                        + UtilityClient.getCurrentTheme().getSeparator()
                        + UtilityClient.getCurrentTheme().getSuffixColor() +  I18n.translate("uc.options.theme.suffix"),
                this.width / 2, this.height / 2 - 70, Color.TEXT.color);
        super.render(mouseX, mouseY, partialTicks);
    }
}

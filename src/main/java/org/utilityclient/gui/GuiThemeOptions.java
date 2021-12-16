package org.utilityclient.gui;

import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.overlay.Theme;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiThemeOptions extends GuiScreen {
    /**
     * The parent GUI for this GUI
     */
    private final GuiScreen parentScreen;

    /**
     * The title of the GUI.
     */
    private String title;

    public GuiThemeOptions(GuiScreen parentScreenIn) {
        this.parentScreen = parentScreenIn;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui() {
        this.title = UtilityClient.getClientName() + " Theme Options";

        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 - 40, 98, 20, "Previous Theme"));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height / 2 - 40, 98, 20, "Next Theme"));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 2, I18n.format("gui.done")));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button.id == 1)
                UtilityClient.CURRENT_THEME = Theme.getThemeById(UtilityClient.CURRENT_THEME.getId() - 1);
            if (button.id == 2)
                UtilityClient.CURRENT_THEME = Theme.getThemeById(UtilityClient.CURRENT_THEME.getId() + 1);

            if (button.id == 200) {
                Config.setInteger(ConfigEntry.SELECTED_THEME, UtilityClient.CURRENT_THEME.getId());
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentScreen);
            }
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, 16777215);
        this.drawCenteredString(this.fontRendererObj, UtilityClient.CURRENT_THEME.getPrefix() + "Prefix§7: " + UtilityClient.CURRENT_THEME.getSuffix() + "Suffix", this.width / 2, this.height / 2 - 70, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

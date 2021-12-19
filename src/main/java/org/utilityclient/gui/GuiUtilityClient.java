package org.utilityclient.gui;

import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.utils.gui.GuiCustomSlider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiUtilityClient extends GuiScreen
{
    /** The parent GUI for this GUI */
    private final GuiScreen parentScreen;

    /** The title of the GUI. */
    private String title;

    public GuiUtilityClient(GuiScreen parentScreenIn)
    {
        this.parentScreen = parentScreenIn;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {

        this.title = UtilityClient.getClientName();

        this.buttonList.add(new GuiCustomSlider(-1, this.width / 2 - 100, this.height/2-110, f -> Config.setFloat(ConfigEntry.ZOOM_FACTOR, f), 0f, 1f, Config.getFloat(ConfigEntry.ZOOM_FACTOR, 0.15f)));

        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height/2-88, I18n.format("uc.options.macro.title")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height/2-66, I18n.format("uc.options.theme.title")));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height/2-44, I18n.format("uc.options.crosshair.title")));

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 2+22, I18n.format("gui.done")));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if(button.id == 1) {
                Config.save();
                this.mc.displayGuiScreen(new GuiThemeOptions(this));
            }

            if(button.id == 3) {
                Config.save();
                this.mc.displayGuiScreen(new GuiCrosshairOptions(this));
            }

            if(button.id == 4) {
                Config.save();
                this.mc.displayGuiScreen(new GuiCreateMacro(this));
            }

            if (button.id == 200)
            {
                Config.save();
                this.mc.displayGuiScreen(this.parentScreen);
            }
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

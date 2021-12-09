package de.gamingcraft.gui;

import de.gamingcraft.UtilityClient;
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

        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height/2-66, "Select Theme"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height/2-44, UtilityClient.capesEnabled ? "§cDisable Capes" : "§aEnable Capes"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height/2-22, "Select Crosshair"));

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
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiThemeOptions(this));
            }

            if(button.id == 2) {
                UtilityClient.capesEnabled = !UtilityClient.capesEnabled;
                this.buttonList.get(1).displayString = UtilityClient.capesEnabled ? "§cDisable Capes" : "§aEnable Capes";

            }

            if(button.id == 3) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiCrosshairOptions(this));
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
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

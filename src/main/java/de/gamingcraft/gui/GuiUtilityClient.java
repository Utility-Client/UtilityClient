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
        int i = 0;
        this.title = UtilityClient.getName();

        //buttons here

        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height/2-20, "Select Theme"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height/2+4, "Comming soon!", false));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height/2+28, "Comming soon!", false));

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
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

            //if(button.id == 2) {
                //this.mc.gameSettings.saveOptions();
                //this.mc.displayGuiScreen(new GuiKeyBindEditor(this));
            //}

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentScreen);
            }

            // Button Ifs here
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, 16777215);

        // Strings go here

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

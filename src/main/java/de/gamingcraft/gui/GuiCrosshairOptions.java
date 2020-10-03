package de.gamingcraft.gui;

import de.gamingcraft.UtilityClient;
import de.gamingcraft.config.ConfigManager;
import de.gamingcraft.overlay.Theme;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiCrosshairOptions extends GuiScreen
{
    /** The parent GUI for this GUI */
    private final GuiScreen parentScreen;

    /** The title of the GUI. */
    private String title;

    public GuiCrosshairOptions(GuiScreen parentScreenIn)
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
        this.title = UtilityClient.getClientName() + " Crosshair Options";

        //buttons here

        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height/2, 98, 20, "Previous Crosshair"));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height/2, 98, 20, "Next Crosshair"));

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
                if(ConfigManager.config.getCrosshair()-1 < 0) {
                    ConfigManager.config.setCrosshair(UtilityClient.CROSSHAIR_MANAGER_INSTANCE.crosshairs.size()-1);
                } else  {
                    ConfigManager.config.setCrosshair(ConfigManager.config.getCrosshair() - 1);
                }
            }

            if(button.id == 2) {
                if((ConfigManager.config.getCrosshair()+1) > (UtilityClient.CROSSHAIR_MANAGER_INSTANCE.crosshairs.size()-1)) {
                    ConfigManager.config.setCrosshair(0);
                } else {
                    ConfigManager.config.setCrosshair(ConfigManager.config.getCrosshair() + 1);
                }
            }

            if (button.id == 200)
            {
                ConfigManager.overrideConfig(UtilityClient.CURRENT_THEME.getId(), ConfigManager.config.getHotkeyZoom(), ConfigManager.config.getHotkeyFulbright(), ConfigManager.config.getCrosshair());
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

        UtilityClient.CROSSHAIR_MANAGER_INSTANCE.loop(-50);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

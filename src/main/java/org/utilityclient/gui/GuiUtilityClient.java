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
    private final GuiScreen parentScreen;
    private String title;
    public GuiUtilityClient(GuiScreen parentScreenIn)
    {
        this.parentScreen = parentScreenIn;
    }

    public void initGui()
    {
        this.title = UtilityClient.getClientName();
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 2+22, I18n.format("gui.done")));
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 200)
            {
                Config.save();
                this.mc.displayGuiScreen(this.parentScreen);
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

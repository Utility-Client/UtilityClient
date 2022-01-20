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
        parentScreen = parentScreenIn;
    }

    public void initGui()
    {
        title = UtilityClient.getClientName();
        buttonList.add(new GuiButton(200, width / 2 - 100, height / 2+22, I18n.format("gui.done")));
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled) {
            if (button.id == 200) {
                Config.save();
                mc.displayGuiScreen(parentScreen);
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, title, width / 2, 20, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

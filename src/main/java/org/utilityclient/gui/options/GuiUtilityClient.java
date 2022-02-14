package org.utilityclient.gui.options;

import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.gui.options.macros.GuiMacroManager;
import org.utilityclient.gui.options.overlay.GuiOverlaySettings;
import org.utilityclient.utils.gui.GuiCustomSlider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiUtilityClient extends GuiScreen
{
    private final GuiScreen parentScreen;
    private String title;
    public GuiUtilityClient(GuiScreen parentScreenIn) {
        parentScreen = parentScreenIn;
    }

    public void initGui() {
        title = UtilityClient.getClientName();

        buttonList.add(new GuiCustomSlider(0, this.width / 2 - 204, this.height/2 - 66, f -> Config.setFloat(ConfigEntry.ZOOM_FACTOR, f), Config.getFloat(ConfigEntry.ZOOM_FACTOR, 0.15f)));
        buttonList.add(new GuiButton(1, width / 2 + 4, height / 2 - 66, Config.getBoolean("keystrokesEnabled", true) ? "Disable Keystrokes" : "Enable Keystrokes"));

        buttonList.add(new GuiButton(2, width / 2 - 204, height / 2 - 44, "Edit Crosshair..."));
        buttonList.add(new GuiButton(3, width / 2 + 4, height / 2 - 44, "Change Theme..."));

        buttonList.add(new GuiButton(4, width / 2 - 204, height / 2 - 22, "Overlay Settings..."));
        buttonList.add(new GuiButton(5, width / 2 + 4, height / 2 - 22, "Manage Macros..."));

        buttonList.add(new GuiButton(6, width / 2 - 204, height / 2, "Discord Settings..."));
        buttonList.add(new GuiButton(200, width / 2 + 4, height / 2, I18n.format("gui.done")));
    }

    public void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled) {
            if (button.id != 1) Config.save();
            switch (button.id) {
                case 200 -> {
                    mc.displayGuiScreen(parentScreen);
                    Config.save();
                }
                case 1 -> {
                    Config.setBoolean("keystrokesEnabled", !Config.getBoolean("keystrokesEnabled", true));
                    buttonList.get(1).displayString = Config.getBoolean("keystrokesEnabled", true) ? "Disable Keystrokes" : "Enable Keystrokes";
                    Config.save();
                }
                case 2 -> mc.displayGuiScreen(new GuiCrosshairOptions(this));
                case 3 -> mc.displayGuiScreen(new GuiThemeOptions(this));
                case 4 -> mc.displayGuiScreen(new GuiOverlaySettings(this));
                case 5 -> mc.displayGuiScreen(new GuiMacroManager(this));
                case 6 -> mc.displayGuiScreen(new GuiDiscordOptions(this));
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, title, width / 2, 20, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

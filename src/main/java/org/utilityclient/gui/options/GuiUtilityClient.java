package org.utilityclient.gui.options;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.gui.options.macros.GuiMacroManager;
import org.utilityclient.gui.options.overlay.GuiOverlaySettings;
import org.utilityclient.utils.gui.GuiCustomSlider;

import java.io.IOException;

public class GuiUtilityClient extends Screen
{
    private final Screen parentScreen;
    private String title;
    public GuiUtilityClient(Screen parentScreenIn) {
        parentScreen = parentScreenIn;
    }

    public void init() {
        title = UtilityClient.getClientName();

        buttons.add(new GuiCustomSlider(0, this.width / 2 - 204, this.height/2 - 66, f -> Config.setFloat(ConfigEntry.ZOOM_FACTOR, f), Config.getFloat(ConfigEntry.ZOOM_FACTOR, 0.15f)));
        buttons.add(new ButtonWidget(1, width / 2 + 4, height / 2 - 66, Config.getBoolean("keystrokesEnabled", true) ? "Disable Keystrokes" : "Enable Keystrokes"));

        buttons.add(new ButtonWidget(2, width / 2 - 204, height / 2 - 44, "Edit Crosshair..."));
        buttons.add(new ButtonWidget(3, width / 2 + 4, height / 2 - 44, "Change Theme..."));

        buttons.add(new ButtonWidget(4, width / 2 - 204, height / 2 - 22, "Overlay Settings..."));
        buttons.add(new ButtonWidget(5, width / 2 + 4, height / 2 - 22, "Manage Macros..."));

        buttons.add(new ButtonWidget(6, width / 2 - 204, height / 2, "Discord Settings..."));
        buttons.add(new ButtonWidget(200, width / 2 + 4, height / 2, I18n.translate("gui.done")));
    }

    public void actionPerformed(ButtonWidget button) throws IOException
    {
        if (button.active) {
            if (button.id != 1) Config.save();
            switch (button.id) {
                case 200 -> {
                    client.openScreen(parentScreen);
                    Config.save();
                }
                case 1 -> {
                    Config.setBoolean("keystrokesEnabled", !Config.getBoolean("keystrokesEnabled", true));
                    buttons.get(1).message = Config.getBoolean("keystrokesEnabled", true) ? "Disable Keystrokes" : "Enable Keystrokes";
                    Config.save();
                }
                case 2 -> client.openScreen(new GuiCrosshairOptions(this));
                case 3 -> client.openScreen(new GuiThemeOptions(this));
                case 4 -> client.openScreen(new GuiOverlaySettings(this));
                case 5 -> client.openScreen(new GuiMacroManager(this));
                case 6 -> client.openScreen(new GuiDiscordOptions(this));
            }
        }
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        drawCenteredString(textRenderer, title, width / 2, 20, 16777215);
        super.render(mouseX, mouseY, partialTicks);
    }
}

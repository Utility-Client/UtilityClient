package org.utilityclient.gui.options;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.resources.I18n;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.utils.Color;

public class GuiDiscordOptions extends Screen {
    private final Screen parent;

    public GuiDiscordOptions(Screen parentIn) {
        parent = parentIn;
    }

    @Override
    public void init() {
        buttons.add(new ButtonWidget(200, width / 2 - 100, height - 30, I18n.translate("gui.done")));
        buttons.add(new ButtonWidget(1, width / 2 - 100, height / 2 - 22, Config.getBoolean(ConfigEntry.DISCORD_RICH_PRESENCE) ? "Disable Rich Presence" : "Enable Rich Presence"));
        buttons.add(new ButtonWidget(2, width / 2 - 100, height / 2, Config.getBoolean(ConfigEntry.DISCORD_FRIEND_NOTIFICATIONS) ? "Disable Friend Notifications" : "Enable Friend Notifications"));
        buttons.add(new ButtonWidget(3, width / 2 - 100, height / 2 + 22, Config.getBoolean(ConfigEntry.DISCORD_SHOW_SERVER) ? "Hide Server in Rich Presence" : "Show Server in Rich Presence"));
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        this.drawCenteredString(textRenderer, "Discord Options", this.width / 2, 20, Color.TEXT.color);
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void buttonClicked(ButtonWidget button) {
        switch (button.id) {
            case 200 -> client.openScreen(parent);
            case 1 -> {
                Config.toggleBoolean(ConfigEntry.DISCORD_RICH_PRESENCE);
                buttons.get(1).message = Config.getBoolean(ConfigEntry.DISCORD_RICH_PRESENCE) ? "Disable Rich Presence" : "Enable Rich Presence";
            }
            case 2 -> {
                Config.toggleBoolean(ConfigEntry.DISCORD_FRIEND_NOTIFICATIONS);
                buttons.get(2).message = Config.getBoolean(ConfigEntry.DISCORD_FRIEND_NOTIFICATIONS) ? "Disable Friend Notifications" : "Enable Friend Notifications";
            }
            case 3 -> {
                Config.toggleBoolean(ConfigEntry.DISCORD_SHOW_SERVER);
                buttons.get(3).message = Config.getBoolean(ConfigEntry.DISCORD_SHOW_SERVER) ? "Hide Server in Rich Presence" : "Show Server in Rich Presence";
            }
        }
    }
}

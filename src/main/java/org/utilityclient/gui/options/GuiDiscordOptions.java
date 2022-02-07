package org.utilityclient.gui.options;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.utils.Color;

public class GuiDiscordOptions extends GuiScreen {
    private final GuiScreen parent;

    public GuiDiscordOptions(GuiScreen parentIn) {
        parent = parentIn;
    }

    @Override
    public void initGui() {
        buttonList.add(new GuiButton(200, width / 2 - 100, height - 30, I18n.format("gui.done")));
        buttonList.add(new GuiButton(1, width / 2 - 100, height / 2 - 22, Config.getBoolean(ConfigEntry.DISCORD_RICH_PRESENCE) ? "Disable Rich Presence" : "Enable Rich Presence"));
        buttonList.add(new GuiButton(2, width / 2 - 100, height / 2, Config.getBoolean(ConfigEntry.DISCORD_FRIEND_NOTIFICATIONS) ? "Disable Friend Notifications" : "Enable Friend Notifications"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Discord Options", this.width / 2, 20, Color.TEXT.color);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 200 -> mc.displayGuiScreen(parent);
            case 1 -> {
                Config.toggleBoolean(ConfigEntry.DISCORD_RICH_PRESENCE);
                buttonList.get(1).displayString = Config.getBoolean(ConfigEntry.DISCORD_RICH_PRESENCE) ? "Disable Rich Presence" : "Enable Rich Presence";
            }
            case 2 -> {
                Config.toggleBoolean(ConfigEntry.DISCORD_FRIEND_NOTIFICATIONS);
                buttonList.get(2).displayString = Config.getBoolean(ConfigEntry.DISCORD_FRIEND_NOTIFICATIONS) ? "Disable Friend Notifications" : "Enable Friend Notifications";
            }
        }
    }
}

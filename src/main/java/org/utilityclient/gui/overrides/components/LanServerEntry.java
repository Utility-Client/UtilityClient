package org.utilityclient.gui.overrides.components;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.network.LanServerQueryManager;
import net.minecraft.client.resource.language.I18n;
import org.utilityclient.gui.overrides.GuiMultiplayer;

public class LanServerEntry implements EntryListWidget.Entry {
    private final GuiMultiplayer parent;
    protected final MinecraftClient client;
    protected final LanServerQueryManager.LanServerInfo serverInfo;
    private long time = 0L;

    protected LanServerEntry(GuiMultiplayer parent, LanServerQueryManager.LanServerInfo serverInfo) {
        this.parent = parent;
        this.serverInfo = serverInfo;
        this.client = MinecraftClient.getInstance();
    }

    public void render(int index, int x, int y, int rowWidth, int rowHeight, int mouseX, int mouseY, boolean hovered) {
        this.client.textRenderer.draw(I18n.translate("lanServer.title"), x + 32 + 3, y + 1, 16777215);
        this.client.textRenderer.draw(this.serverInfo.getMotd(), x + 32 + 3, y + 12, 8421504);
        if (this.client.options.hideServerAddress) {
            this.client.textRenderer.draw(I18n.translate("selectServer.hiddenAddress"), x + 32 + 3, y + 12 + 11, 3158064);
        } else {
            this.client.textRenderer.draw(this.serverInfo.getAddressPort(), x + 32 + 3, y + 12 + 11, 3158064);
        }

    }

    public boolean mouseClicked(int index, int mouseX, int mouseY, int button, int x, int y) {
        this.parent.selectEntry(index);
        if (MinecraftClient.getTime() - this.time < 250L) {
            this.parent.connect();
        }

        this.time = MinecraftClient.getTime();
        return false;
    }

    public void updatePosition(int index, int x, int y) {
    }

    public void mouseReleased(int index, int mouseX, int mouseY, int button, int x, int y) {
    }

    public LanServerQueryManager.LanServerInfo getServer() {
        return this.serverInfo;
    }
}

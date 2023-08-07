package org.utilityclient.gui.overrides;

import net.minecraft.client.gui.screen.AchievementsScreen;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.realms.RealmsBridge;
import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.gui.components.GuiScreen;
import org.utilityclient.overlay.modules.DistanceModule;

import java.io.IOException;

public class GuiIngameMenu extends GuiScreen {

    public GuiIngameMenu() {
        super("Game paused");
    }

    public void init() {
        buttons.clear();
        int i = -16;
        buttons.add(new ButtonWidget(1, this.width / 2 - 100, this.height / 4 + 120 + i, client.isIntegratedServerRunning() ? I18n.translate("menu.returnToMenu") : I18n.translate("menu.disconnect")));
        buttons.add(new ButtonWidget(4, this.width / 2 - 100, this.height / 4 + 24 + i, I18n.translate("menu.returnToGame")));
        buttons.add(new ButtonWidget(0, this.width / 2 - 100, this.height / 4 + 72 + i + 11, I18n.translate("menu.options")));
        buttons.add(new ButtonWidget(5, this.width / 2 - 100, this.height / 4 + 48 + i + 11, I18n.translate("gui.achievements")));
        buttons.add(new ButtonWidget(99, this.width - 210, 10, "Toggle Streamer Mode"));
        if(DistanceModule.gotUpdated) buttons.add(new ButtonWidget(98, this.width - 210, 30, "Clear Destination"));
    }

    protected void buttonClicked(ButtonWidget button)
    {
        switch (button.id)
        {
            case 0:
                client.openScreen(new GuiOptions(this, client.options));
                break;

            case 1:
                boolean bl = this.client.isIntegratedServerRunning();
                boolean bl2 = this.client.isConnectedToRealms();
                button.active = false;
                this.client.world.disconnect();
                this.client.connect(null);
                if (bl) {
                    this.client.openScreen(new TitleScreen());
                } else if (bl2) {
                    RealmsBridge realmsBridge = new RealmsBridge();
                    realmsBridge.switchToRealms(new TitleScreen());
                } else {
                    // TODO: Change to custom Multiplayer Screen
                    this.client.openScreen(new MultiplayerScreen(new TitleScreen()));
                }
                try {
                    Config.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            case 2:
            case 3:
            default:
                break;

            case 4:
                client.openScreen(null);
                client.closeScreen();
                break;

            case 5:
                client.openScreen(new AchievementsScreen(this, client.player.getStatHandler()));
                break;

            case 6:
                client.openScreen(new StatsScreen(this, client.player.getStatHandler()));
                break;

            case 7:
                client.openScreen(new OpenToLanScreen(this));

            case 99:
                UtilityClient.streamerMode = !UtilityClient.streamerMode;
                break;

            case 98:
                DistanceModule.gotUpdated = false;
                button.visible = false;
                break;
        }
    }

    public void render(int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground();
        this.drawCenteredString(textRenderer, I18n.translate("menu.game"), this.width / 2, 40, 16777215);
        super.render(mouseX, mouseY, partialTicks);
    }
}

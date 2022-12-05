package org.utilityclient.gui.overrides;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.screen.multiplayer.LanScanWidget;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.network.LanServerQueryManager;
import net.minecraft.client.network.MultiplayerServerListPinger;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.options.ServerList;
import net.minecraft.client.resource.language.I18n;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class GuiMultiplayer extends Screen implements IdentifibleBooleanConsumer
{
    private static final Logger LOGGER = LogManager.getLogger();
    private final MultiplayerServerListPinger pinger = new MultiplayerServerListPinger();
    private final Screen parent;
    private MPServerListWidget serverListWidget;
    private ServerList serverList;
    private ButtonWidget editButton;
    private ButtonWidget joinButton;
    private ButtonWidget deleteButton;
    private boolean field_1191;
    private boolean field_1192;
    private boolean field_1193;
    private boolean field_1194;
    private String tooltipText;
    private ServerInfo selectedEntry;
    private LanServerQueryManager.LanServerEntryList lanServers;
    private LanServerQueryManager.LanServerDetector lanServerDetector;
    private boolean initialized;

    private TextFieldWidget directConnectTextField;

    public GuiMultiplayer(Screen screen) {
        parent = screen;
    }

    public void init()
    {
        Keyboard.enableRepeatEvents(true);
        buttons.clear();
        if (!initialized) {
            initialized = true;
            serverList = new ServerList(client);
            serverList.loadFile();
            lanServers = new LanServerQueryManager.LanServerEntryList();

            try {
                lanServerDetector = new LanServerQueryManager.LanServerDetector(lanServers);
                lanServerDetector.start();
            } catch (Exception var2) {
                LOGGER.warn("Unable to start LAN server detection: " + var2.getMessage());
            }

            serverListWidget = new MPServerListWidget(this, client, width, height, 32, height - 64, 36);
            serverListWidget.setServers(serverList);
        } else {
            serverListWidget.updateBounds(width, height, 32, height - 64);
        }

        initButtons();
    }

    public void handleMouse() {
        super.handleMouse();
        serverListWidget.handleMouse();
    }


    public void initButtons()
    {   // widthIn: 75
        buttons.add(editButton = new ButtonWidget(7, width / 2 - 154, height - 28, 100, 20, I18n.translate("selectServer.edit")));
        buttons.add(deleteButton = new ButtonWidget(2, width / 2 - 50, height - 28, 100, 20, I18n.translate("selectServer.delete")));
        buttons.add(joinButton = new ButtonWidget(1, width / 2 - 154, height - 52, 100, 20, I18n.translate("selectServer.select")));
        //buttonList.add(new ButtonWidget(4, width / 2 - 50, height - 52, 100, 20, I18n.translate("selectServer.direct")));
        buttons.add(new ButtonWidget(3, width / 2 + 4 + 50, height - 52, 100, 20, I18n.translate("selectServer.add")));
        buttons.add(new ButtonWidget(8, width / 2 - 154, height - 52, 204, 20, I18n.translate("selectServer.refresh")));
        buttons.add(new ButtonWidget(0, width / 2 + 4 + 50, height - 28, 100, 20, I18n.translate("gui.cancel")));

        directConnectTextField = new TextFieldWidget(11, textRenderer, width / 2 - 154, height - 76, 202, 20);
        directConnectTextField.setMaxLength(128);
        directConnectTextField.setFocused(true);
        directConnectTextField.setText(client.options.lastServer);

        buttons.add(new ButtonWidget(10, width / 2 + 4 + 50, height - 76, 100, 20, I18n.translate("selectServer.select")));

        selectEntry(serverListWidget.getSelected());
    }

    public void tick() {
        super.tick();
        if (lanServers.needsUpdate()) {
            List<LanServerQueryManager.LanServerInfo> list = lanServers.getServers();
            lanServers.markClean();
            serverListWidget.addServers(list);
        }

        pinger.tick();
    }

    public void removed() {
        Keyboard.enableRepeatEvents(false);
        if (lanServerDetector != null) {
            lanServerDetector.interrupt();
            lanServerDetector = null;
        }

        pinger.cancel();
    }

    protected void buttonClicked(ButtonWidget button)
    {
        if (button.active)
        {
            EntryListWidget.Entry entry = serverListWidget.getSelected() < 0 ? null : serverListWidget.getEntry(serverListWidget.getSelected());

            if (button.id == 10)
                client.openScreen(
                        new ConnectScreen(this, client,
                                new ServerInfo("Direct Connect", directConnectTextField.getText(), false)));


            if (button.id == 2 && entry instanceof ServerEntry) {
                String string = ((ServerEntry)entry).getServer().name;
                if (string != null) {
                    this.field_1191 = true;
                    String string2 = I18n.translate("selectServer.deleteQuestion");
                    String string3 = "'" + string + "' " + I18n.translate("selectServer.deleteWarning");
                    String string4 = I18n.translate("selectServer.deleteButton");
                    String string5 = I18n.translate("gui.cancel");
                    ConfirmScreen confirmScreen = new ConfirmScreen(this, string2, string3, string4, string5, this.serverListWidget.getSelected());
                    this.client.openScreen(confirmScreen);
                }
            }
            else if (button.id == 1) connect();
            else if (button.id == 4)
            {
                field_1194 = true;
                client.openScreen(new DirectConnectScreen(this, selectedEntry = new ServerInfo(I18n.translate("selectServer.defaultName"), "", false)));
            }
            else if (button.id == 3)
            {
                field_1192 = true;
                client.openScreen(new AddServerScreen(this, selectedEntry = new ServerInfo(I18n.translate("selectServer.defaultName"), "", false)));
            }
            else if (button.id == 7 && entry instanceof ServerEntry) {
                field_1193 = true;
                ServerInfo serverInfo = ((ServerEntry)entry).getServer();
                selectedEntry = new ServerInfo(serverInfo.name, serverInfo.address, false);
                selectedEntry.copyFrom(serverInfo);
                client.openScreen(new AddServerScreen(this, selectedEntry));
            }
            else if (button.id == 0) client.openScreen(parent);
            else if (button.id == 8) refresh();
        }
    }

    private void refresh() {
        this.client.openScreen(new MultiplayerScreen(this.parent));
    }


    public void confirmResult(boolean b, int id)
    {
        EntryListWidget.Entry entry = this.serverListWidget.getSelected() < 0 ? null : this.serverListWidget.getEntry(this.serverListWidget.getSelected());
        if (this.field_1191) {
            this.field_1191 = false;
            if (b && entry instanceof ServerEntry) {
                this.serverList.remove(this.serverListWidget.getSelected());
                this.serverList.saveFile();
                this.serverListWidget.setSelected(-1);
                this.serverListWidget.setServers(this.serverList);
            }

            this.client.openScreen(this);
        } else if (this.field_1194) {
            this.field_1194 = false;
            if (b) {
                this.connect(this.selectedEntry);
            } else {
                this.client.openScreen(this);
            }
        } else if (this.field_1192) {
            this.field_1192 = false;
            if (b) {
                this.serverList.add(this.selectedEntry);
                this.serverList.saveFile();
                this.serverListWidget.setSelected(-1);
                this.serverListWidget.setServers(this.serverList);
            }

            this.client.openScreen(this);
        } else if (this.field_1193) {
            this.field_1193 = false;
            if (b && entry instanceof ServerEntry) {
                ServerInfo serverInfo = ((ServerEntry)entry).getServer();
                serverInfo.name = this.selectedEntry.name;
                serverInfo.address = this.selectedEntry.address;
                serverInfo.copyFrom(this.selectedEntry);
                this.serverList.saveFile();
                this.serverListWidget.setServers(this.serverList);
            }

            this.client.openScreen(this);
        }

    }

    protected void keyPressed(char character, int code)
    {
        directConnectTextField.keyPressed(character, code);
        int i = this.serverListWidget.getSelected();
        EntryListWidget.Entry entry = i < 0 ? null : this.serverListWidget.getEntry(i);
        if (code == 63) {
            this.refresh();
        } else {
            if (i >= 0) {
                if (code == 200) {
                    if (hasShiftDown()) {
                        if (i > 0 && entry instanceof ServerEntry) {
                            this.serverList.swapEntries(i, i - 1);
                            this.selectEntry(this.serverListWidget.getSelected() - 1);
                            this.serverListWidget.scroll(-this.serverListWidget.getItemHeight());
                            this.serverListWidget.setServers(this.serverList);
                        }
                    } else if (i > 0) {
                        this.selectEntry(this.serverListWidget.getSelected() - 1);
                        this.serverListWidget.scroll(-this.serverListWidget.getItemHeight());
                        if (this.serverListWidget.getEntry(this.serverListWidget.getSelected()) instanceof LanScanWidget) {
                            if (this.serverListWidget.getSelected() > 0) {
                                this.selectEntry(this.serverListWidget.getEntryCount() - 1);
                                this.serverListWidget.scroll(-this.serverListWidget.getItemHeight());
                            } else {
                                this.selectEntry(-1);
                            }
                        }
                    } else {
                        this.selectEntry(-1);
                    }
                } else if (code == 208) {
                    if (hasShiftDown()) {
                        if (i < this.serverList.size() - 1) {
                            this.serverList.swapEntries(i, i + 1);
                            this.selectEntry(i + 1);
                            this.serverListWidget.scroll(this.serverListWidget.getItemHeight());
                            this.serverListWidget.setServers(this.serverList);
                        }
                    } else if (i < this.serverListWidget.getEntryCount()) {
                        this.selectEntry(this.serverListWidget.getSelected() + 1);
                        this.serverListWidget.scroll(this.serverListWidget.getItemHeight());
                        if (this.serverListWidget.getEntry(this.serverListWidget.getSelected()) instanceof LanScanWidget) {
                            if (this.serverListWidget.getSelected() < this.serverListWidget.getEntryCount() - 1) {
                                this.selectEntry(this.serverListWidget.getEntryCount() + 1);
                                this.serverListWidget.scroll(this.serverListWidget.getItemHeight());
                            } else {
                                this.selectEntry(-1);
                            }
                        }
                    } else {
                        this.selectEntry(-1);
                    }
                } else if (code != 28 && code != 156) {
                    super.keyPressed(character, code);
                } else {
                    this.buttonClicked(this.buttons.get(2));
                }
            } else {
                super.keyPressed(character, code);
            }

        }
    }

    public void render(int mouseX, int mouseY, float tickDelta) {
        this.tooltipText = null;
        this.renderBackground();
        this.serverListWidget.render(mouseX, mouseY, tickDelta);
        this.drawCenteredString(this.textRenderer, I18n.translate("multiplayer.title"), this.width / 2, 20, 16777215);
        directConnectTextField.render();
        super.render(mouseX, mouseY, tickDelta);
        if (this.tooltipText != null) {
            this.renderTooltip(Lists.newArrayList(Splitter.on("\n").split(this.tooltipText)), mouseX, mouseY);
        }

    }
    public void connect() {
        EntryListWidget.Entry entry = serverListWidget.getSelected() < 0 ? null : serverListWidget.getEntry(serverListWidget.getSelected());
        if (entry instanceof ServerEntry) {
            connect(((ServerEntry)entry).getServer());
        } else if (entry instanceof LanServerEntry) {
            LanServerQueryManager.LanServerInfo lanServerInfo = ((LanServerEntry)entry).getServer();
            connect(new ServerInfo(lanServerInfo.getMotd(), lanServerInfo.getAddressPort(), true));
        }

    }

    private void connect(ServerInfo entry) {
        client.openScreen(new ConnectScreen(this, client, entry));
    }


    public void selectEntry(int index) {
        serverListWidget.setSelected(index);
        EntryListWidget.Entry entry = index < 0 ? null : serverListWidget.getEntry(index);
        joinButton.active = false;
        editButton.active = false;
        deleteButton.active = false;
        if (entry != null && !(entry instanceof LanScanWidget)) {
            joinButton.active = true;
            if (entry instanceof ServerEntry) {
                editButton.active = true;
                deleteButton.active = true;
            }
        }

    }

    public MultiplayerServerListPinger getServerListPinger() {
        return pinger;
    }

    public void setTooltip(String text) {
        tooltipText = text;
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        serverListWidget.mouseClicked(mouseX, mouseY, mouseButton);
        directConnectTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        super.mouseReleased(mouseX, mouseY, state);
        serverListWidget.mouseReleased(mouseX, mouseY, state);
    }

    public ServerList getServerList()
    {
        return serverList;
    }

    public boolean canSortUp(int index) {
        return index > 0;
    }

    public boolean canSortDown(int index) {
        return index < serverList.size() - 1;
    }

    public void sortUp(int index, boolean shiftPressed) {
        int i = shiftPressed ? 0 : index - 1;
        serverList.swapEntries(index, i);
        if (serverListWidget.getSelected() == index) {
            selectEntry(i);
        }

        serverListWidget.setServers(serverList);
    }

    public void sortDown(int index, boolean shiftPressed) {
        int i = shiftPressed ? serverList.size() - 1 : index + 1;
        serverList.swapEntries(index, i);
        if (serverListWidget.getSelected() == index) {
            selectEntry(i);
        }

        serverListWidget.setServers(serverList);
    }
}

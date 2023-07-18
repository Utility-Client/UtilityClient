package org.utilityclient.gui.overrides.components;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.LanScanWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.network.LanServerQueryManager;
import net.minecraft.client.options.ServerList;
import org.utilityclient.gui.overrides.GuiMultiplayer;

import java.util.Iterator;
import java.util.List;

public class MPServerListWidget extends EntryListWidget {
    private final GuiMultiplayer parent;
    private final List<ServerEntry> servers = Lists.newArrayList();
    private final List<LanServerEntry> lanServers = Lists.newArrayList();
    private final EntryListWidget.Entry scanningWidget = new LanScanWidget();
    private int selectedEntry = -1;

    public MPServerListWidget(GuiMultiplayer parent, MinecraftClient client, int width, int height, int top, int bottom, int entryHeight) {
        super(client, width, height, top, bottom, entryHeight);
        this.parent = parent;
    }

    public EntryListWidget.Entry getEntry(int index) {
        if (index < this.servers.size()) {
            return this.servers.get(index);
        } else {
            index -= this.servers.size();
            if (index == 0) {
                return this.scanningWidget;
            } else {
                --index;
                return (EntryListWidget.Entry)this.lanServers.get(index);
            }
        }
    }

    public int getEntryCount() {
        return this.servers.size() + 1 + this.lanServers.size();
    }

    public void setSelected(int index) {
        this.selectedEntry = index;
    }

    protected boolean isEntrySelected(int index) {
        return index == this.selectedEntry;
    }

    public int getSelected() {
        return this.selectedEntry;
    }

    public void setServers(ServerList servers) {
        this.servers.clear();

        for(int i = 0; i < servers.size(); ++i) {
            this.servers.add(new ServerEntry(this.parent, servers.get(i)));
        }

    }

    public void addServers(List<LanServerQueryManager.LanServerInfo> servers) {
        this.lanServers.clear();
        Iterator iterator = servers.iterator();

        while(iterator.hasNext()) {
            LanServerQueryManager.LanServerInfo lanServerInfo = (LanServerQueryManager.LanServerInfo)iterator.next();
            this.lanServers.add(new LanServerEntry(this.parent, lanServerInfo));
        }

    }

    protected int getScrollbarPosition() {
        return super.getScrollbarPosition() + 30;
    }

    public int getRowWidth() {
        return super.getRowWidth() + 85;
    }
}


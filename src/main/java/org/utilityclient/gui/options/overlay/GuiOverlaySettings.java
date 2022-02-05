package org.utilityclient.gui.options.overlay;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static org.utilityclient.overlay.ModuleHandler.modules;

public class GuiOverlaySettings extends GuiScreen {
    private final GuiScreen parent;

    public GuiOverlaySettings(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButton(200, width / 2, height / 12 * 10, I18n.format("gui.done")));
        buttonList.add(new GuiButton(1, width / 2 - 200, height / 12 * 10, Config.getBoolean(ConfigEntry.OVERLAY_BACKGROUND, true) ? "Disable Background" : "Enable Background"));

        int offset = 0;
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).shouldRender())
                buttonList.add(new GuiButton(1000 + i, width / 4 * 2, (height / 10 * 2) + ((i + offset) * 21), height / 4, 20, modules.get(i).isEnabled ? "Disable" : "Enable"));
            else offset -= 1;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, "Overlay Settings", width / 2, 20, 16777215);

        int offset = 0;
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).shouldRender())
                drawString(fontRendererObj, modules.get(i).getName(), width / 3, (height / 10 * 2) + ((i + offset) * 21) + 10, 16777215);
            else offset -= 1;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        switch (button.id) {
            case 1 -> {
                Config.setBoolean(ConfigEntry.OVERLAY_BACKGROUND, !Config.getBoolean(ConfigEntry.OVERLAY_BACKGROUND, true));
                button.displayString = Config.getBoolean(ConfigEntry.OVERLAY_BACKGROUND, true) ? "Disable Background" : "Enable Background";
                Config.save();
            }

            case 200 -> {
                mc.displayGuiScreen(parent);
                saveStates();
            }
        }

        if (button.id >= 1000) {
            int modId = button.id - 1000;
            modules.get(modId).isEnabled = !modules.get(modId).isEnabled;
            buttonList.forEach(btn -> {
                if(btn.id >= 1000) {
                    int modId_ = btn.id - 1000;
                    btn.displayString = modules.get(modId_).isEnabled ? "Disable" : "Enable";
                }
            });
        }
    }

    public static void saveStates() throws IOException {
        for (int i = 0; i < modules.size(); i++) {
            File f = new File("uc2/modules/" + i + ".txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f, false);
            fw.write(Boolean.toString(modules.get(i).isEnabled));
            fw.close();
        }
    }

    public static void loadStates() throws IOException {
        for (int i = 0; i < modules.size(); i++) {
            File f = new File("uc2/modules/" + i + ".txt");
            if (f.createNewFile()) {
                FileWriter fw = new FileWriter(f, false);
                fw.write(Boolean.toString(modules.get(i).isEnabled));
                fw.close();
            } else {
                Scanner sc = new Scanner(f);
                modules.get(i).isEnabled = !sc.hasNextBoolean() || sc.nextBoolean();
            }
        }
    }
}

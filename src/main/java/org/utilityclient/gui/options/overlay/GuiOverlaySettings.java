package org.utilityclient.gui.options.overlay;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.utils.Color;
import org.utilityclient.utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.utilityclient.overlay.ModuleHandler.modules;

public class GuiOverlaySettings extends Screen {
    private final Screen parent;

    public GuiOverlaySettings(Screen parent) {
        this.parent = parent;
    }

    @Override
    public void init() {
        super.init();
        buttons.add(new ButtonWidget(200, width / 2, height / 12 * 10, I18n.translate("gui.done")));
        buttons.add(new ButtonWidget(1, width / 2 - 200, height / 12 * 10, Config.getBoolean(ConfigEntry.OVERLAY_BACKGROUND) ? "Disable Background" : "Enable Background"));

        int offset = 0;
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).shouldRender())
                buttons.add(new ButtonWidget(1000 + i, width / 4 * 2, (height / 10 * 2) + ((i + offset) * 21), height / 4, 20, modules.get(i).isEnabled ? "Disable" : "Enable"));
            else offset -= 1;
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        drawCenteredString(textRenderer, "Overlay Settings", width / 2, 20, Color.TEXT.color);

        int offset = 0;
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).shouldRender())
                textRenderer.draw(modules.get(i).getName(), width / 3, (height / 10 * 2) + ((i + offset) * 21) + 10, Color.TEXT.color);
            else offset -= 1;
        }

        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void buttonClicked(ButtonWidget button) {
        super.buttonClicked(button);

        switch (button.id) {
            case 1:
                Config.setBoolean(ConfigEntry.OVERLAY_BACKGROUND, !Config.getBoolean(ConfigEntry.OVERLAY_BACKGROUND));
                button.message = Config.getBoolean(ConfigEntry.OVERLAY_BACKGROUND) ? "Disable Background" : "Enable Background";
                try {
                    Config.save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;

            case 200:
                client.openScreen(parent);
                try {
                    saveStates();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;
        }

        if (button.id >= 1000) {
            int modId = button.id - 1000;
            modules.get(modId).isEnabled = !modules.get(modId).isEnabled;
            buttons.forEach(btn -> {
                if(btn.id >= 1000) {
                    int modId_ = btn.id - 1000;
                    btn.message = modules.get(modId_).isEnabled ? "Disable" : "Enable";
                }
            });
        }
    }

    public static void saveStates() throws IOException {
        for (int i = 0; i < modules.size(); i++) {
            File f = new File("uc3/modules/" + i + ".txt");
            Utils.ignore(f.createNewFile());
            FileWriter fw = new FileWriter(f, false);
            fw.write(Boolean.toString(modules.get(i).isEnabled));
            fw.close();
        }
    }

    public static void loadStates() throws IOException {
        for (int i = 0; i < modules.size(); i++) {
            File f = new File("uc3/modules/" + i + ".txt");
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

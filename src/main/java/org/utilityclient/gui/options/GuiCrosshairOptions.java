package org.utilityclient.gui.options;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.crosshair.CrosshairManager;
import org.utilityclient.utils.Color;
import org.utilityclient.utils.SerializationUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class GuiCrosshairOptions extends Screen
{
    private final Screen parentScreen;
    private String title;
    private int size = 9;
    public static final File crosshairFile = new File("uc2/crosshair.txt");
    HashMap<Integer, Boolean> pixels = new HashMap<>();

    public GuiCrosshairOptions(Screen parentScreenIn)
    {
        parentScreen = parentScreenIn;
    }

    public void initGui()
    {
        title = I18n.translate("uc.options.crosshair.title");
        try {
            size = Config.getInteger(ConfigEntry.CROSSHAIR_SIZE);

            Scanner scanner = new Scanner(crosshairFile);
            pixels = (HashMap) SerializationUtils.deserialize(scanner.nextLine());
            CrosshairManager.pixels = pixels;
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void actionPerformed(ButtonWidget button) throws IOException
    {
        if(button.id < 200) pixels.put(button.id, !pixels.getOrDefault(button.id, true));

        if (button.active)
        {
            if (button.id == 200) {
                FileWriter fw = new FileWriter(crosshairFile, false);
                fw.write(SerializationUtils.serialize(pixels));
                CrosshairManager.pixels = pixels;
                fw.close();

                Config.setInteger(ConfigEntry.CROSSHAIR_SIZE, size);
                Config.save();
                client.openScreen(parentScreen);
            }

            if (button.id == 201) if(size > 1) {
                size--;
                Config.setInteger(ConfigEntry.CROSSHAIR_SIZE, size);
                pixels.clear();
            }

            if (button.id == 202) if(size < 12) {
                size++;
                Config.setInteger(ConfigEntry.CROSSHAIR_SIZE, size);
                pixels.clear();
            }
        }
    }

    public void render(int mouseX, int mouseY, float partialTicks)
    {
        renderBackground();
        drawCenteredString(textRenderer, title, width / 2, 20, Color.TEXT.color);
        drawCenteredString(textRenderer, size + "x" + size, width / 2, height / 4 * 3 + 5, Color.TEXT.color);

        buttons.clear();
        int f = 0;
        for (int i = 0; i < size; i++) {
            for (int e = 0; e < size; e++) {
                ButtonWidget t = new ButtonWidget(f,
                        width / 2 + i * 20 - size * 10,
                        height / 2 + e * 20 - size * 10,
                        20, 20,
                        "");
                t.active = !pixels.getOrDefault(f, true);
                buttons.add(t);
                f++;
            }
        }

        buttons.add(new ButtonWidget(201, width / 2 - 100, height / 4 * 3, 20, 20, "-"));
        buttons.add(new ButtonWidget(202, width / 2 + 80, height / 4 * 3, 20, 20, "+"));
        buttons.add(new ButtonWidget(200, width / 2 - 100, height / 8 * 7, I18n.translate("gui.done")));

        super.render(mouseX, mouseY, partialTicks);
    }
}
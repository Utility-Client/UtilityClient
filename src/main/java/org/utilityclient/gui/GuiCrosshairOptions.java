package org.utilityclient.gui;

import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.crosshair.CrosshairManager;
import org.utilityclient.utils.SerializationUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class GuiCrosshairOptions extends GuiScreen
{
    private final GuiScreen parentScreen;
    private String title;
    private int size = 9;
    public static final File crosshairFile = new File("uc2/crosshair.txt");
    HashMap<Integer, Boolean> pixels = new HashMap<>();

    public GuiCrosshairOptions(GuiScreen parentScreenIn)
    {
        this.parentScreen = parentScreenIn;
    }

    public void initGui()
    {
        this.title = "Crosshair Editor";
        try {
            size = Config.getInteger(ConfigEntry.CROSSHAIR_SIZE);

            Scanner scanner = new Scanner(crosshairFile);
            pixels = (HashMap<Integer, Boolean>) SerializationUtils.deserialize(scanner.nextLine());
            CrosshairManager.pixels = pixels;
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if(button.id < 200) pixels.put(button.id, !pixels.getOrDefault(button.id, true));

        if (button.enabled)
        {
            if (button.id == 200) {
                FileWriter fw = new FileWriter(crosshairFile, false);
                fw.write(SerializationUtils.serialize(pixels));
                CrosshairManager.pixels = pixels;
                fw.close();

                Config.setInteger(ConfigEntry.CROSSHAIR_SIZE, size);
                Config.save();
                this.mc.displayGuiScreen(this.parentScreen);
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

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, 16777215);
        this.drawCenteredString(this.fontRendererObj, size + "x" + size, this.width / 2, this.height / 4 * 3 + 5, 16777215);

        buttonList.clear();
        int f = 0;
        for (int i = 0; i < size; i++) {
            for (int e = 0; e < size; e++) {
                buttonList.add(new GuiButton(f,
                        this.width / 2 + i * 20 - size * 10,
                        this.height / 2 + e * 20 - size * 10,
                        20, 20,
                        "", !pixels.getOrDefault(f, true)));
                f++;
            }
        }

        this.buttonList.add(new GuiButton(201, this.width / 2 - 100, this.height / 4 * 3, 20, 20, "-"));
        this.buttonList.add(new GuiButton(202, this.width / 2 + 80, this.height / 4 * 3, 20, 20, "+"));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 8 * 7, I18n.format("gui.done")));

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

package org.utilityclient.gui.options.macros;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import org.lwjgl.input.Keyboard;
import org.utilityclient.macro.Macro;
import org.utilityclient.macro.MacroManager;
import org.utilityclient.utils.Color;
import org.utilityclient.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;

import static org.utilityclient.macro.MacroManager.macros;

public class GuiMacroManager extends Screen {
    public final Screen parent;

    public GuiMacroManager(Screen parent) {
        this.parent = parent;
    }

    @Override
    public void init() {
        super.init();
        buttons.add(new ButtonWidget(200, width / 2, height - 30, I18n.translate("gui.done")));
        buttons.add(new ButtonWidget(201, width / 2 - 200, height - 30, "Create Macro..."));
        for (int i = 0; i < macros.size(); i++) {
            int gridSize = width / 7;
            int y = (height / 10 * 2) + (i * 21);
            buttons.add(new ButtonWidget(i + 1000, gridSize * 4, y, gridSize, 20, "Edit"));
            buttons.add(new ButtonWidget(i + 2000, gridSize * 5, y, gridSize, 20, "Delete"));
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        drawCenteredString(textRenderer, "Macros", width / 2, 20, Color.TEXT.color);

        int gridSize = width / 7;
        float x = height / 10f * 2;
        int z = -21;

        textRenderer.drawWithShadow("Name", gridSize, x + z + 10, Color.TEXT.color);
        textRenderer.drawWithShadow("Message", gridSize * 2, x + z + 10, Color.TEXT.color);
        textRenderer.drawWithShadow("Key", gridSize * 3, x + z + 10, Color.TEXT.color);

        for (int i = 0; i < macros.size(); i++) {
            Macro macro = macros.get(i);
            float y = x + (i * 21) + 10;
            textRenderer.drawWithShadow(macro.name, gridSize, y, Color.TEXT.color);
            textRenderer.drawWithShadow(macro.message, gridSize * 2, y, Color.TEXT.color);
            textRenderer.drawWithShadow(Keyboard.getKeyName(macro.keyCode), gridSize * 3, y, Color.TEXT.color);
        }

        super.render(mouseX, mouseY, partialTicks);

        buttons.forEach(btn -> {
            if(btn.id >= 2000) if (btn.isHovered()) // TODO might be broken
                textRenderer.drawWithShadow("After a restart, the macro will disappear completely.",
                        mouseX - textRenderer.getStringWidth("After a restart, the macro will disappear completely.") / 2f, mouseY+12, Color.TEXT.color);
        });
    }

    @Override
    protected void buttonClicked(ButtonWidget button) {
        super.buttonClicked(button);
        if (button.id == 200) client.openScreen(parent);
        if (button.id == 201) client.openScreen(new GuiCreateMacro(this));

        if (button.id >= 1000) {
            int id;
            if (button.id >= 2000) {
                id = button.id - 2000;
                // Delete
                new File("uc3/macros/" + macros.get(id).name + ".txt").deleteOnExit(); // Delete macro file
                try {
                    MacroManager.reload(); // Reload macros
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                client.openScreen(new GuiMacroManager(parent)); // Refresh screen
                return;
            }
            id = button.id - 1000;
            // Edit
            Utils.ignore(macros.get(id).file.delete()); // Delete macro file
            client.openScreen(new GuiCreateMacro(this, macros.get(id))); // Show "Edit Macro" Dialog
        }
    }
}

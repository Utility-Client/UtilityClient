package org.utilityclient.gui.options.macros;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.utilityclient.macro.Macro;
import org.utilityclient.macro.MacroManager;
import org.utilityclient.utils.Color;
import org.utilityclient.utils.Utils;

import java.io.File;
import java.io.IOException;
import static org.utilityclient.macro.MacroManager.*;

public class GuiMacroManager extends GuiScreen {
    public final GuiScreen parent;

    public GuiMacroManager(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButton(200, width / 2, height - 30, I18n.format("gui.done")));
        buttonList.add(new GuiButton(201, width / 2 - 200, height - 30, "Create Macro..."));
        for (int i = 0; i < macros.size(); i++) {
            int gridSize = width / 7;
            int y = (height / 10 * 2) + (i * 21);
            buttonList.add(new GuiButton(i + 1000, gridSize * 4, y, gridSize, 20, "Edit"));
            buttonList.add(new GuiButton(i + 2000, gridSize * 5, y, gridSize, 20, "Delete"));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, "Macros", width / 2, 20, Color.TEXT.color);

        int gridSize = width / 7;
        float x = height / 10f * 2;
        int z = -21;

        fontRendererObj.drawStringWithShadow("Name", gridSize, x + z + 10, Color.TEXT.color);
        fontRendererObj.drawStringWithShadow("Message", gridSize * 2, x + z + 10, Color.TEXT.color);
        fontRendererObj.drawStringWithShadow("Key", gridSize * 3, x + z + 10, Color.TEXT.color);

        for (int i = 0; i < macros.size(); i++) {
            Macro macro = macros.get(i);
            float y = x + (i * 21) + 10;
            fontRendererObj.drawStringWithShadow(macro.name, gridSize, y, Color.TEXT.color);
            fontRendererObj.drawStringWithShadow(macro.message, gridSize * 2, y, Color.TEXT.color);
            fontRendererObj.drawStringWithShadow(Keyboard.getKeyName(macro.keyCode), gridSize * 3, y, Color.TEXT.color);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);

        buttonList.forEach(btn -> {
            if(btn.id >= 2000) if (btn.getHoverState(btn.isMouseOver()) == 2)
                fontRendererObj.drawStringWithShadow("After a restart, the macro will disappear completely.",
                        mouseX - fontRendererObj.getStringWidth("After a restart, the macro will disappear completely.") / 2f, mouseY+12, Color.TEXT.color);
        });
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button.id == 200) mc.displayGuiScreen(parent);
        if (button.id == 201) mc.displayGuiScreen(new GuiCreateMacro(this));

        if (button.id >= 1000) {
            int id;
            if (button.id >= 2000) {
                id = button.id - 2000;
                // Delete
                new File("uc2/macros/" + macros.get(id).name + ".txt").deleteOnExit(); // Delete macro file
                MacroManager.reload(); // Reload macros
                mc.displayGuiScreen(new GuiMacroManager(parent)); // Refresh screen
                return;
            }
            id = button.id - 1000;
            // Edit
            Utils.ignore(macros.get(id).file.delete()); // Delete macro file
            mc.displayGuiScreen(new GuiCreateMacro(this, macros.get(id))); // Show "Edit Macro" Dialog
        }
    }
}

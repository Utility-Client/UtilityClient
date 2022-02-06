package org.utilityclient.gui.options.macros;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.utilityclient.macro.Macro;

import java.io.IOException;
import static org.utilityclient.macro.MacroManager.*;

public class GuiMacroManager extends GuiScreen {
    private final GuiScreen parent;

    public GuiMacroManager(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButton(200, width / 2, height / 12 * 10, I18n.format("gui.done")));

        for (int i = 0; i < macros.size(); i++) {
            Macro macro = macros.get(i);
            
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if (button.id == 200) mc.displayGuiScreen(parent);

    }
}

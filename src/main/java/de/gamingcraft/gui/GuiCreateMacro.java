package de.gamingcraft.gui;

import de.gamingcraft.macro.Macro;
import de.gamingcraft.macro.MacroManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class GuiCreateMacro extends GuiScreen {
    private final GuiScreen parentScreen;
    private String title;
    private GuiTextField nameInput;
    private GuiTextField messageInput;

    public GuiCreateMacro(GuiScreen parentScreenIn)
    {
        this.parentScreen = parentScreenIn;
    }

    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        title = "Create Macro";

        nameInput = new GuiTextField(1, fontRendererObj, width / 2 - 100, height / 2 - 66, 200, 20);
        nameInput.setMaxStringLength(255);
        nameInput.setFocused(true);
        nameInput.setText("New Macro");

        messageInput = new GuiTextField(2, fontRendererObj, width / 2 - 100, height / 2 - 22, 200, 20);
        messageInput.setMaxStringLength(100);
        messageInput.setFocused(false);
        messageInput.setText("Insert a message or command here");

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 2 + 22, 100, 20, "Cancel"));
        this.buttonList.add(new GuiButton(201, this.width / 2, this.height / 2 + 22, 100, 20, "Save"));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, 16777215);


        nameInput.drawTextBox();
        messageInput.drawTextBox();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void updateScreen()
    {
        nameInput.updateCursorCounter();
        messageInput.updateCursorCounter();
    }

    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 200) this.mc.displayGuiScreen(this.parentScreen);

        if (button.id == 201) {
            try {
                MacroManager.save(nameInput.getText(), new Macro(nameInput.getText(), messageInput.getText(), 0));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mc.displayGuiScreen(this.parentScreen);
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        nameInput.mouseClicked(mouseX, mouseY, mouseButton);
        messageInput.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        nameInput.textboxKeyTyped(typedChar, keyCode);
        messageInput.textboxKeyTyped(typedChar, keyCode);
    }
}

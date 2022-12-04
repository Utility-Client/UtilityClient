package org.utilityclient.gui.options.macros;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resource.language.I18n;
import org.utilityclient.macro.Macro;
import org.utilityclient.macro.MacroManager;
import org.lwjgl.input.Keyboard;
import java.io.IOException;

public class GuiCreateMacro extends Screen {
    private final Screen parentScreen;
    private String title;
    private TextFieldWidget nameInput;
    private TextFieldWidget messageInput;
    private int currentKeyCode = 0;
    private boolean listeningForInput;
    private boolean editMode = false;
    private Macro macro;

    /**
     * Create
     * @param parentScreenIn The parent screen
     */
    public GuiCreateMacro(Screen parentScreenIn)
    {
        this.parentScreen = parentScreenIn;
    }

    /**
     * Edit
     * @param parentScreenIn The parent screen
     * @param macroIn The macro, that should be edited.
     */
    public GuiCreateMacro(Screen parentScreenIn, Macro macroIn) {
        this.parentScreen = parentScreenIn;
        editMode = true;
        macro = macroIn;
        currentKeyCode = macro.keyCode;
    }

    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        title = I18n.translate("uc.options.macro.title");

        nameInput = new TextFieldWidget(1, textRenderer, width / 2 - 100, height / 2 - 66 +1, 124, 18);
        nameInput.setMaxLength(255);
        nameInput.setFocused(true);
        nameInput.setText(editMode ? macro.name : I18n.translate("uc.options.macro.default.name"));

        messageInput = new TextFieldWidget(2, textRenderer, width / 2 - 100, height / 2 - 22 +1, 200, 18);
        messageInput.setMaxLength(100);
        messageInput.setFocused(false);
        messageInput.setText(editMode ? macro.message : I18n.translate("uc.options.macro.default.message"));

        buttons.add(new ButtonWidget(200, width / 2 - 100, this.height / 2 + 22, 100, 20, I18n.translate("uc.options.macro.cancel")));
        buttons.add(new ButtonWidget(201, width / 2, this.height / 2 + 22, 100, 20, I18n.translate("uc.options.macro.save")));
        buttons.get(buttons.size()-1).active = editMode; // TODO: might break
        buttons.add(new ButtonWidget(202, width / 2 + 25, height / 2 - 66, 75, 20, "None"));

        if(editMode) buttons.get(2).message = Keyboard.getKeyName(macro.keyCode);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        renderBackground();
        this.drawCenteredString(textRenderer, this.title, this.width / 2, 20, 16777215);

        textRenderer.drawWithShadow(I18n.translate("uc.options.macro.name"), this.width / 2f - 100, height / 2f - 77, 16777215);
        textRenderer.drawWithShadow(I18n.translate("uc.options.macro.message"), this.width / 2f - 100, height / 2f - 33, 16777215);
        nameInput.render();
        messageInput.render();

        super.render(mouseX, mouseY, partialTicks);
    }

    public void updateScreen()
    {
        // FIXME
        // nameInput.updateCursorCounter();
        // messageInput.updateCursorCounter();
    }

    protected void actionPerformed(ButtonWidget button)
    {
        if (button.id == 200) {
            client.openScreen(this.parentScreen);
        }

        if (button.id == 201) {
            if (currentKeyCode != 0) {
                buttons.get(1).active = true;
                try {
                    MacroManager.save(editMode ? macro.name : nameInput.getText(), new Macro(nameInput.getText(), messageInput.getText(), currentKeyCode));
                } catch (IOException e) {
                    e.printStackTrace(System.out);
                }

                if(editMode) {
                    try {
                        MacroManager.reload();
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                    // FIXME
                    //client.openScreen(new GuiMacroManager(((GuiMacroManager) parentScreen).parent));
                } else {
                    client.openScreen(this.parentScreen);
                }
            }else {
                buttons.get(1).active = false;
            }
        }

        if(button.id == 202) {
            listeningForInput = !listeningForInput;
            if (listeningForInput) button.message = "Press a key...";
            else button.message = Keyboard.getKeyName(currentKeyCode);
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        nameInput.mouseClicked(mouseX, mouseY, mouseButton);
        messageInput.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if(listeningForInput) {
            onInput(keyCode);
        }else {
            nameInput.keyPressed(typedChar, keyCode);
            messageInput.keyPressed(typedChar, keyCode);
        }

    }

    public void onInput(int keyCode) {
        if (keyCode != 1) currentKeyCode = keyCode;
        listeningForInput = false;
        buttons.get(2).message = Keyboard.getKeyName(currentKeyCode);
        buttons.get(1).active = true;
    }
}

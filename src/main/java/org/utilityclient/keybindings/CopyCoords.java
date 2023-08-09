package org.utilityclient.keybindings;

import org.utilityclient.api.KeyBinding;
import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.ChatFormatting;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

@Register @StandaloneCompatible
public class CopyCoords extends KeyBinding {
    public CopyCoords() {
        super("Compass", "Copy Coordinates", 0x2E, false, false, true);
    }

    @Override
    public void up() {
        String text = uc().wrapper.getPlayerPosition(" ");
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        uc().wrapper.writeChatMessage(ChatFormatting.GREEN + "Copied coordinates to the clipboard.");
    }
}

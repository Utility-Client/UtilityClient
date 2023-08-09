package org.utilityclient.keybindings;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.text.LiteralText;
import org.lwjgl.input.Keyboard;
import org.utilityclient.api.KeyBinding;
import org.utilityclient.api.Register;
import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.overlay.Compass;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

@Register @StandaloneCompatible
public class PasteCoords extends KeyBinding {
    public PasteCoords() {
        super("Compass", "Paste coordinates to the Compass", Keyboard.KEY_V, false, false, true);
    }

    @Override
    public void up() {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable t = clipboard.getContents(clipboard);
            if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String s = (String) t.getTransferData(DataFlavor.stringFlavor);
                String[] coords = s.split(" ");
                if (coords.length < 3) throw new IndexOutOfBoundsException();
                Compass.destX = Integer.parseInt(coords[0]);
                Compass.destY = Integer.parseInt(coords[2]);
                Compass.active = true;
                uc().wrapper.writeChatMessage(ChatFormatting.GREEN + "Destination updated.");
            }
        } catch (Exception e) {
            uc().wrapper.writeChatMessage(ChatFormatting.RED + "Error while updating destination.");
        }
    }
}

package org.utilityclient.keybindings;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.text.LiteralText;
import org.lwjgl.input.Keyboard;
import org.utilityclient.api.KeyBinding;
import org.utilityclient.api.Register;
import org.utilityclient.overlay.Compass;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

@Register
public class PasteCoords extends KeyBinding {
    public PasteCoords() {
        super("General", "Paste coordinates", Keyboard.KEY_V, false, false, true);
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
                Compass.gotUpdated = true;
                mc().inGameHud.getChatHud().addMessage(new LiteralText(ChatFormatting.GREEN + "Destination updated."));
            }
        } catch (Exception e) {
            mc().inGameHud.getChatHud().addMessage(new LiteralText(ChatFormatting.RED + "Error while updating destination."));
        }
    }
}

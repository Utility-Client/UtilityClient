package org.utilityclient.keybindings;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import org.lwjgl.input.Keyboard;
import org.utilityclient.api.KeyBinding;
import org.utilityclient.api.Register;
import org.utilityclient.overlay.modules.DistanceModule;

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
                DistanceModule.x = Integer.parseInt(coords[0]);
                DistanceModule.y = Integer.parseInt(coords[1]);
                DistanceModule.z = Integer.parseInt(coords[2]);
                DistanceModule.gotUpdated = true;
                mc().inGameHud.getChatHud().addMessage(new LiteralText(ChatFormatting.GREEN + "Destination updated."));
            }
        } catch (Exception e) {
            mc().inGameHud.getChatHud().addMessage(new LiteralText(ChatFormatting.RED + "Error while updating destination."));
        }
    }
}

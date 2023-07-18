package org.utilityclient.overlay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.Window;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;

public class Keystrokes {
    public static void loop() {
        if(!Config.getBoolean(ConfigEntry.KEYSTROKES)) return;
        Window window = new Window(MinecraftClient.getInstance());
        int offset = 0;
        if (Config.getBoolean(ConfigEntry.SPRINT_AND_CROUCH_KEYSTROKES)) {
            offset = 24;
            renderButton(window.getWidth() - 72, window.getHeight() - 24, 32, "Sprint", MinecraftClient.getInstance().options.keySprint);
            renderButton(window.getWidth() - 34, window.getHeight() - 24, 32, "Sneak", MinecraftClient.getInstance().options.keySneak);
        }

        renderButton(window.getWidth() - 48, window.getHeight() - 72 - offset, 20, "^", MinecraftClient.getInstance().options.keyForward);
        renderButton(window.getWidth() - 48, window.getHeight() - 48 - offset, 20, "v", MinecraftClient.getInstance().options.keyBack);
        renderButton(window.getWidth() - 72, window.getHeight() - 48 - offset, 20, "<", MinecraftClient.getInstance().options.keyLeft);
        renderButton(window.getWidth()-24, window.getHeight()-48-offset, 20, ">", MinecraftClient.getInstance().options.keyRight);
        renderButton(window.getWidth()-72, window.getHeight()-24-offset, 68, "Jump", MinecraftClient.getInstance().options.keyJump);
    }

    public static void renderButton(int x, int y, int width, String label, KeyBinding active) {
        ButtonWidget bw = new ButtonWidget(0, x, y, width, 20, label);
        bw.active = active.isPressed();
        bw.render(MinecraftClient.getInstance(), 0, 0);
    }
}

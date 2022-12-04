package org.utilityclient.overlay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.utilityclient.config.Config;

public class Keystrokes {
    public static void loop() {
        if(!Config.getBoolean("keystrokesEnabled", true)) return;
        int width = MinecraftClient.getInstance().width;
        int height = MinecraftClient.getInstance().height;

        ButtonWidget a = new ButtonWidget(0, width-48, height-72, 20, 20, "W");
        a.active = MinecraftClient.getInstance().options.keyForward.isPressed();
        a.render(MinecraftClient.getInstance(), 0, 0);

        ButtonWidget b = new ButtonWidget(0, width-72, height-48, 20, 20, "A");
        b.active = MinecraftClient.getInstance().options.keyLeft.isPressed();
        b.render(MinecraftClient.getInstance(), 0, 0);

        ButtonWidget c = new ButtonWidget(0, width-48, height-48, 20, 20, "S");
        c.active = MinecraftClient.getInstance().options.keyBack.isPressed();
        c.render(MinecraftClient.getInstance(), 0, 0);

        ButtonWidget d = new ButtonWidget(0, width-24, height-48, 20, 20, "D");
        d.active = MinecraftClient.getInstance().options.keyRight.isPressed();
        d.render(MinecraftClient.getInstance(), 0, 0);

        ButtonWidget e = new ButtonWidget(0, width-72, height-24, 68, 20, "Space");
        e.active = MinecraftClient.getInstance().options.keyJump.isPressed();
        e.render(MinecraftClient.getInstance(), 0, 0);
    }
}

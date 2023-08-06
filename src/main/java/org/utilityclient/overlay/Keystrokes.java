package org.utilityclient.overlay;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;
import org.lwjgl.input.Keyboard;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.utils.Color;

public class Keystrokes {
    //private static final DrawableHelper drawableHelper = new DrawableHelper();

    static Identifier inactive = new Identifier("textures/keystrokes/inactive.png");
    static Identifier active = new Identifier("textures/keystrokes/active.png");

    public static void loop() {
        if(!Config.getBoolean(ConfigEntry.KEYSTROKES)) return;
        Window window = new Window(MinecraftClient.getInstance());
        int offset = 0;
        if (Config.getBoolean(ConfigEntry.SPRINT_AND_CROUCH_KEYSTROKES)) {
            offset = 24;
            render(window.getWidth() - 72, window.getHeight() - 24, KeyType.Modifiers, MinecraftClient.getInstance().options.keySprint);
            render(window.getWidth() - 36, window.getHeight() - 24, KeyType.Modifiers, MinecraftClient.getInstance().options.keySneak);
        }

        render(window.getWidth() - 48, window.getHeight() - 72 - offset, KeyType.Direction, MinecraftClient.getInstance().options.keyForward);
        render(window.getWidth() - 48, window.getHeight() - 48 - offset, KeyType.Direction, MinecraftClient.getInstance().options.keyBack);
        render(window.getWidth() - 72, window.getHeight() - 48 - offset, KeyType.Direction, MinecraftClient.getInstance().options.keyLeft);
        render(window.getWidth()-24, window.getHeight()-48-offset, KeyType.Direction, MinecraftClient.getInstance().options.keyRight);
        render(window.getWidth()-72, window.getHeight()-24-offset, KeyType.Jump, MinecraftClient.getInstance().options.keyJump);
    }

    public static void render(int x, int y, KeyType type, KeyBinding kb) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        MinecraftClient.getInstance().getTextureManager().bindTexture(kb.isPressed() ? active : inactive);
        DrawableHelper.drawTexture(x, y, type.x, type.y, type.width, type.height, type.width / 4, type.height / 4, 80 * 4, 76 * 4);
        GlStateManager.disableBlend();

        String keyName = Keyboard.getKeyName(kb.getCode());
        if (keyName.contains("CONTROL")) keyName = "CTRL";
        if (keyName.contains("SHIFT")) keyName = "SHIFT";
        if (keyName.contains("ALT")) keyName = "ALT";

        MinecraftClient.getInstance().textRenderer.draw(keyName, ((x + type.width / 8f) - MinecraftClient.getInstance().textRenderer.getStringWidth(keyName) / 2f), y+6, Color.TEXT.color, false);
    }

    enum KeyType {
        Direction(4, 20),
        Jump(28, 68),
        Modifiers(52, 32);

        public final int x = 24, y, width, height = 80;

        KeyType(int y, int w) {
            this.y = y * 4;
            this.width = w * 4;
        }
    }
}

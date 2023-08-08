package org.utilityclient.overlay;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.Identifier;
import org.utilityclient.api.Instances;
import org.utilityclient.utils.Color;
import org.utilityclient.utils.RenderHelper;

public class Compass extends Instances {
    public static final Identifier compassTexture = new Identifier("textures/items/compass.png");
    public static final int spriteUnit = 16, sprites = 32;
    public static int destX, destY;
    public static boolean active = false;
    private int currentSprite = 0;
    private double distance;

    public void render(int x, int y) {
        if (!active) return;
        update();
        RenderHelper.texture(x, y, 32, 32,
                compassTexture, 0, spriteUnit * currentSprite, spriteUnit, spriteUnit,
                spriteUnit, spriteUnit * sprites, false);
        String text = ChatFormatting.YELLOW + "" + Math.round(distance) + " Block(s) away";
        if (distance <= 3) text = ChatFormatting.GREEN + "You arrived.";
        int textX = (int) (x - (mc().textRenderer.getStringWidth(text) / 2f) + 16), textY = y + 41, margin = 3;
        RenderHelper.rect(textX - margin, textY - margin, mc().textRenderer.getStringWidth(text) + 2 * margin, mc().textRenderer.fontHeight + 2 * margin,
                Color.Background.color, 1f, true);
        mc().textRenderer.drawWithShadow(text, textX, textY, Color.SnowWhite.color);
    }

    private void update() {
        double relDestX = destX - mc().player.getPos().x, relDestY = destY - mc().player.getPos().z; // Calculate relative coordinates
        double normalizedHeadYaw = ((mc().player.getHeadRotation()%360)-270)*-1; // Normalize head yaw
        double theta = Math.toDegrees(Math.atan2(relDestY, relDestX)) - 180d + normalizedHeadYaw; // Calculate rotation to the destination
        if (theta < 0.0d) theta += 360.0;
        currentSprite = (int) Math.round(theta / 360 * sprites); // Apply compass sprite
        distance = Math.sqrt(Math.pow(relDestX, 2) + Math.pow(relDestY, 2)); // Apply distance to the destination
    }
}

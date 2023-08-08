package org.utilityclient.overlay;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;
import org.utilityclient.api.Instances;
import org.utilityclient.utils.RenderHelper;

public class Compass extends Instances {
    public static final Identifier compassTexture = new Identifier("textures/items/compass.png");
    public static final int spriteUnit = 16;
    public static final int sprites = 32;
    private int position = 0;
    public static int destX, destY;
    public static double distance;
    public static boolean gotUpdated = false;

    public void render(int x, int y) {
        if (!gotUpdated) return;
        update();
        RenderHelper.texture(x, y, 32, 32, compassTexture, 0, spriteUnit * position, spriteUnit, spriteUnit, spriteUnit, spriteUnit * sprites, false);
        String text = ChatFormatting.YELLOW + "" + Math.round(distance) + " Block(s) away";
        if (distance <= 3) text = ChatFormatting.GREEN + "You arrived.";
        mc().textRenderer.drawWithShadow(text, x - (mc().textRenderer.getStringWidth(text)/2f) + 16, y + 43, -1);
    }

    private void update() {
        double playerX = mc().player.getPos().x, playerY = mc().player.getPos().z;
        double relDestX = destX - playerX, relDestY = destY - playerY;

        double normalizedHeadYaw = (mc().player.getHeadRotation()%360)-270;
        normalizedHeadYaw *= -1;

        double theta = Math.toDegrees(Math.atan2(relDestY, relDestX)) - 180d + normalizedHeadYaw;
        //theta *= -1;

        if (theta < 0.0d) theta += 360.0;

        double selSprite = theta / 360 * sprites;
        position = (int) Math.round(selSprite);
        distance = Math.sqrt(Math.pow(relDestX, 2) + Math.pow(relDestY, 2));
    }
}

import de.gamingcraft.UtilityClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import static net.minecraft.client.gui.Gui.drawRect;

public class UtilityClientAPI {
    public static void renderText(String text, int x, int y) {
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(text, x, y, -1);
    }

    public static void drawRectangle(double left, double top, double right, double bottom, int color) {
        drawRect(left, top, right, bottom, color);
    }

    public static void addKeyBind(String name, int keyCode) {
        UtilityClient.addKeyBind(name, keyCode, false);
    }

    public static int getScaledWidth() {
        return (new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth();
    }

    public static int getScaledHeight() {
        return (new ScaledResolution(Minecraft.getMinecraft())).getScaledHeight();
    }
}
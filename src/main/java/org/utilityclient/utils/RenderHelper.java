package org.utilityclient.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.util.Identifier;

/**
 * Helper methods for drawing textures and rectangles.
 * @author Sam302
 * @since 3.0
 */
public class RenderHelper {
    /**
     * Draws a rectangle from a texture to the screen.
     * @param screenX Position on the screen at the x-axis.
     * @param screenY Position on the screen at the y-axis.
     * @param screenWidth Width of the rect on the screen.
     * @param screenHeight Height of the rect on the screen.
     * @param texture The identifier of the texture, which shall be used.
     * @param textureX Position on the texture at the x-axis.
     * @param textureY Position on the texture at the y-axis.
     * @param textureWidth Width of the rect on the texture.
     * @param textureHeight Height of the rect on the texture.
     * @param totalTextureWidth Total width of the texture. Required for calculations.
     * @param totalTextureHeight Total height of the texture. Required for calculations.
     * @param blend Enables opacity/transparency for this texture.
     * @since 3.0
     */
    public static void texture(int screenX, int screenY, int screenWidth, int screenHeight, Identifier texture, int textureX, int textureY, int textureWidth, int textureHeight, int totalTextureWidth, int totalTextureHeight, boolean blend) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (blend) GlStateManager.enableBlend();
        MinecraftClient.getInstance().getTextureManager().bindTexture(texture);
        DrawableHelper.drawTexture(screenX, screenY, textureX, textureY, textureWidth, textureHeight, screenWidth, screenHeight, totalTextureWidth, totalTextureHeight);
        if (blend) GlStateManager.disableBlend();
    }

    /**
     * Draws a rectangle to the screen.
     * @param x Position on the screen at the x-axis.
     * @param y Position on the screen at the y-axis.
     * @param width Width of the rectangle.
     * @param height Height of the rectangle.
     * @param color Color of the rectangle. (Example: 0xffffff -> White)
     * @param alpha The opacity from 0.0 to 1.0.
     * @param blend Enables opacity/transparency for this rectangle.
     * @since 3.0
     */
    public static void rect(int x, int y, int width, int height, int color, float alpha, boolean blend) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, alpha);
        if (blend) GlStateManager.enableBlend();
        DrawableHelper.fill(x, y, x + width, y + height, color);
        if (blend) GlStateManager.disableBlend();
    }
}

package org.utilityclient.utils;

import org.utilityclient.UtilityClient;
import org.utilityclient.api.abstraction.StandaloneCompatible;

/**
 * Helper methods for drawing textures and rectangles.
 * @author Sam302
 * @since 3.0
 */
@StandaloneCompatible
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
    public static void texture(int screenX, int screenY, int screenWidth, int screenHeight, String texture, int textureX, int textureY, int textureWidth, int textureHeight, int totalTextureWidth, int totalTextureHeight, boolean blend) {
        UtilityClient.getInstance().wrapper.texture(screenX, screenY, screenWidth, screenHeight, texture, textureX, textureY, textureWidth, textureHeight, totalTextureWidth, totalTextureHeight, blend);
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
        UtilityClient.getInstance().wrapper.rect(x, y, width, height, color, alpha, blend);
    }
}

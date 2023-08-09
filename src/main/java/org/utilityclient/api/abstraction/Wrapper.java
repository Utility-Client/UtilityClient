package org.utilityclient.api.abstraction;

import org.utilityclient.UtilityClient;
import org.utilityclient.utils.MathUtil;

/**
 * Wrapper for all methods outside UtilityClient's scope. (LWJGL, Minecraft, ...)
 * @since 3.0
 * @author Sam302
 */
@StandaloneCompatible
public abstract class Wrapper {
    public static int fontHeight = 9;

    public Wrapper() {
        UtilityClient.setInstance(new UtilityClient(this));
    }

    public abstract boolean isKeyDown(int keyCode);
    public abstract void sendChatMessage(String msg);
    public abstract void writeChatMessage(String msg);
    public abstract String getPlayerBiome();
    public abstract String getPlayerPosition(String seperationString);
    public abstract float getGamma();
    public abstract void setGamma(float gamma);
    public abstract int getLatency();
    public abstract boolean isSingleplayer();
    public abstract char[] getPlayerFacing();
    public abstract int getStringWidth(String input);
    public abstract void drawStringWithShadow(String text, int x, int y, int color);
    public abstract float getPlayerHeadRotation();
    public abstract MathUtil.Vector3<Double> getPlayerPosition();
    public abstract void drawUtilityClientBackground(int width, int height, String id);
    public abstract void rect(int x, int y, int width, int height, int color, float alpha, boolean blend);
    public abstract void texture(int screenX, int screenY, int screenWidth, int screenHeight, String texture, int textureX, int textureY, int textureWidth, int textureHeight, int totalTextureWidth, int totalTextureHeight, boolean blend);
    public abstract void rect(int x1, int y1, int x2, int y2, int color);
    public abstract void drawString(String text, float x, float y, int color, boolean shadow);
}

package org.utilityclient.impl;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import org.lwjgl.input.Keyboard;
import org.utilityclient.api.abstraction.Uncommon;
import org.utilityclient.api.abstraction.Wrapper;
import org.utilityclient.utils.MathUtil;

@Uncommon
public class WrapperImpl extends Wrapper {
    private MinecraftClient mc() {
        return MinecraftClient.getInstance();
    }

    @Override
    public boolean isKeyDown(int keyCode) {
        return Keyboard.isKeyDown(keyCode);
    }

    @Override
    public void sendChatMessage(String msg) {
        MinecraftClient.getInstance().player.sendChatMessage(msg);
    }

    @Override
    public void writeChatMessage(String msg) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(msg));
    }

    @Override
    public String getPlayerBiome() {
        return mc().world.getChunk(mc().player.getBlockPos()).getBiomeAt(mc().player.getBlockPos(), mc().world.getBiomeSource()).name;
    }

    @Override
    public String getPlayerPosition(String seperationString) {
        return    mc().player.getBlockPos().getX() + seperationString
                + mc().player.getBlockPos().getY() + seperationString
                + mc().player.getBlockPos().getZ();
    }

    @Override
    public float getGamma() {
        return mc().options.gamma;
    }

    @Override
    public void setGamma(float gamma) {
        mc().options.gamma = gamma;
    }

    @Override
    public int getLatency() {
        return mc().player.networkHandler.getPlayerListEntry(mc().player.getUuid()).getLatency();
    }

    @Override
    public boolean isSingleplayer() {
        return mc().isInSingleplayer();
    }

    @Override
    public char[] getPlayerFacing() {
        return mc().getCameraEntity().getHorizontalDirection().getName().toCharArray();
    }

    @Override
    public int getStringWidth(String input) {
        return mc().textRenderer.getStringWidth(input);
    }

    @Override
    public void drawStringWithShadow(String text, int x, int y, int color) {
        mc().textRenderer.drawWithShadow(text, x, y, color);
    }

    @Override
    public float getPlayerHeadRotation() {
        return mc().player.getHeadRotation();
    }

    @Override
    public MathUtil.Vector3<Double> getPlayerPosition() {
        return new MathUtil.Vector3<>
                (mc().player.getPos().x, mc().player.getPos().y, mc().player.getPos().z);
    }

    @Override
    public void drawUtilityClientBackground(int width, int height, String id) {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc().getTextureManager().bindTexture(new Identifier(id));
        DrawableHelper.drawTexture(0, 0, 0, 0, width, height, width, height, width, height);
    }

    @Override
    public void rect(int x, int y, int width, int height, int color, float alpha, boolean blend) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, alpha);
        if (blend) GlStateManager.enableBlend();
        DrawableHelper.fill(x, y, x + width, y + height, color);
        if (blend) GlStateManager.disableBlend();
    }

    @Override
    public void texture(int screenX, int screenY, int screenWidth, int screenHeight, String texture, int textureX, int textureY, int textureWidth, int textureHeight, int totalTextureWidth, int totalTextureHeight, boolean blend) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableAlphaTest(); // TODO: Add as parameter?
        if (blend) GlStateManager.enableBlend();
        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(texture));
        DrawableHelper.drawTexture(screenX, screenY, textureX, textureY, textureWidth, textureHeight, screenWidth, screenHeight, totalTextureWidth, totalTextureHeight);
        if (blend) GlStateManager.disableBlend();
        GlStateManager.disableAlphaTest();
    }

    @Override
    public void rect(int x1, int y1, int x2, int y2, int color) {
        Screen.fill(x1, y1, x2, y2, color);
    }

    @Override
    public void drawString(String text, float x, float y, int color, boolean shadow) {
        mc().textRenderer.draw(text, x, y, color, shadow);
    }
}

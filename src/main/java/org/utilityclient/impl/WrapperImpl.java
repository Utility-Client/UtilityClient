package org.utilityclient.impl;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import org.lwjgl.input.Keyboard;
import org.utilityclient.api.abstraction.Wrapper;
import org.utilityclient.utils.MathUtil;

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
}

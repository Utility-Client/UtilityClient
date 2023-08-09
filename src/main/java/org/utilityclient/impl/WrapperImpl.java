package org.utilityclient.impl;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import org.lwjgl.input.Keyboard;
import org.utilityclient.api.abstraction.Wrapper;

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
    public String getPlayerCoords(String seperationString) {
        return null;
    }

    @Override
    public float getGamma() {
        return 0;
    }

    @Override
    public void setGamma(float gamma) {

    }

    @Override
    public int getLatency() {
        return 0;
    }
}

package org.utilityclient.impl;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import org.lwjgl.input.Keyboard;
import org.utilityclient.api.Wrapper;

public class WrapperImpl extends Wrapper {
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
}

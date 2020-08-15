package de.gamingcraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

public class UtilityClient {
    private static String CLIENT_NAME = "Utility Client";
    private static String CLIENT_VERSION = "2.0";

    public static float fovModifier = 1.0f;

    public static ArrayList<KeyBinding> keyBinds = new ArrayList<>();

    public static void startup() {
        Discord.start();
        addKeyBind("Zoom", 46);
        addKeyBind("Fulbright", 50);
    }

    public static void loop() {
        if(keyBinds.get(0).isKeyDown()) {
            fovModifier = 0.25f;
        }else {
            fovModifier = 1.0f;
        }

        if(keyBinds.get(1).isPressed()) {
            if(Minecraft.getMinecraft().gameSettings.gammaSetting == 1.0f) {
                Minecraft.getMinecraft().gameSettings.gammaSetting = 999999;
            } else {
                Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
            }

        }
    }

    public static String getName() {
        return CLIENT_NAME;
    }

    public static String getVersion() {
        return CLIENT_VERSION;
    }

    public static KeyBinding addKeyBind(String name, int keyCode) {
        KeyBinding kb = new KeyBinding(name, keyCode, "Utility Client");
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, kb);
        keyBinds.add(kb);
        return kb;
    }
}

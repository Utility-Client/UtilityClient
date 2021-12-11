package de.gamingcraft.macro;

import de.gamingcraft.UtilityClient;
import de.gamingcraft.utils.FileUtils;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;

public class MacroManager {

    private static File macroFile = new File("uc2/macros.txt");
    private static String macroFileTemplate = "Example:Hello World!:0";
    private static String macroFileContent = "";
    private static Macro[] macros;


    public static void start() throws IOException {
        macroFile.createNewFile();
        if(macroFile.exists()) {
            macroFileContent = FileUtils.loadFile(macroFile);
        }else {
            FileUtils.saveFile(macroFile, macroFileTemplate);
            macroFileContent = macroFileTemplate;
        }
        macros = convertToMacros(macroFileContent);
        for (Macro macro : macros) macro.setKeyBinding(UtilityClient.addKeyBind(macro.getName(), macro.getKeyCode(), true));
    }

    
    public static void loop() {
        for (Macro macro : macros) if(macro.getKeyBinding().isPressed()) Minecraft.getMinecraft().thePlayer.sendChatMessage(macro.getCommand());
    }

    private static Macro[] convertToMacros(String raw) {
        String[] cutted = raw.split("#");
        Macro[] _macros = new Macro[] {};

        for (String c: cutted) {
            String[] c1 = c.split(":");
            if(c1.length == 2) _macros = ArrayUtils.add(_macros, new Macro(c1[0], c1[1], 0)); else _macros = ArrayUtils.add(_macros, new Macro(c1[0], c1[1], Integer.parseInt(c1[2])));
        }

        return _macros;
    }
}

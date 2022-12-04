package org.utilityclient.macro;

import net.minecraft.client.MinecraftClient;
import org.utilityclient.UtilityClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MacroManager {
    public static File macrosFolder = new File("uc2/macros");
    public static ArrayList<Macro> macros = new ArrayList<>();

    public static void run() throws IOException {
        // Create folder + example macro if none exists already.
        if (macrosFolder.mkdirs()) {
            File exampleMacro = new File("uc2/macros/example.txt");
            if (exampleMacro.createNewFile()) {
                FileWriter fw = new FileWriter(exampleMacro, false);
                fw.write("Example Macro\nHello World!\n34");
                fw.close();
            }
        }

        // Load all macros
        for (File macro : Objects.requireNonNull(macrosFolder.listFiles())) load(macro);
    }

    public static void load(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String name, message;
        int keyCode;

        if(scanner.hasNextLine()) name = scanner.nextLine();
        else name = "No name specified";

        if(scanner.hasNextLine()) message = scanner.nextLine();
        else message = "No message specified";

        if(scanner.hasNextInt()) keyCode = scanner.nextInt();
        else keyCode = 0;

        macros.add(new Macro(name, message, keyCode, UtilityClient.addKeyBind(name, keyCode, true), file));
    }

    public static void save(String filename, Macro macro) throws IOException {
        File macroFile = new File("uc2/macros/" + filename + ".txt");
        if(!macroFile.createNewFile()) System.out.println("Macro " + filename + " already exists. Overwriting...");
        FileWriter fw = new FileWriter(macroFile, false);
        fw.write(macro.name + "\n" + macro.message + "\n" + macro.keyCode);
        fw.close();
        load(macroFile);
    }

    public static void loop() {
        for (Macro macro : macros) if(macro.keyBinding.isPressed()) MinecraftClient.getInstance().player.sendChatMessage(macro.message);
    }

    public static void reload() throws FileNotFoundException {
        UtilityClient.keyBinds.removeIf(kb -> kb.getCategory().equalsIgnoreCase("Macros"));
        macros.clear();
        for (File macro : Objects.requireNonNull(macrosFolder.listFiles())) load(macro);
    }
}

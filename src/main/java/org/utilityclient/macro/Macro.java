package org.utilityclient.macro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Custom keybindings, which send automatically chat messages or commands.
 * @author Sam302
 * @since 3.0
 */
public class Macro {
    protected final File file;
    public String Name, Message;
    public int KeyCode;

    public Macro(String fileName) throws FileNotFoundException {
        file = new File("uc3/macros/" + fileName + ".txt");
        Name = fileName;
        loadFromFile();
    }

    public Macro(String fileName, String msg, int keyCode, boolean forceOverride) throws IOException {
        file = new File("uc3/macros/" + fileName + ".txt");
        Name = fileName;

        if (file.exists() && !forceOverride) {
            loadFromFile();
        } else {
            Message = msg;
            KeyCode = keyCode;
            saveToFile();
        }

    }

    protected void loadFromFile() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        if (s.hasNextLine()) Message = s.nextLine();
        if (s.hasNextInt()) KeyCode = s.nextInt();
        s.close();
    }

    protected void saveToFile() throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(Message + "\n");
        fw.write(KeyCode + "");
        fw.close();
    }

    public boolean deleteFile() {
        return file.delete();
    }
}

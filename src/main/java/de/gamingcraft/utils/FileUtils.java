package de.gamingcraft.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {
    public static String loadFile(File file) throws FileNotFoundException {
        StringBuilder str = new StringBuilder();
        Scanner s = new Scanner(file);
        while(s.hasNextLine()) {
            str.append(s.nextLine()).append("#");
        }
        s.close();
        return str.toString();
    }

    public static void saveFile(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }
}

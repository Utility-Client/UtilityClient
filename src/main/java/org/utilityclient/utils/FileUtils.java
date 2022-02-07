package org.utilityclient.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtils {
    public static String[] loadFile(File file) throws FileNotFoundException {
        ArrayList<String> str = new ArrayList<>();
        Scanner s = new Scanner(file);
        while(s.hasNextLine()) {
            str.add(s.nextLine());
        }
        s.close();
        return str.toArray(new String[] {});
    }

    public static void saveFile(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }
}

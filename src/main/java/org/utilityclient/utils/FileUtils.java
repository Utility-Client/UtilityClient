package org.utilityclient.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Easy loading and saving of files.
 * @since 2.9.1
 * @author GamingCraft
 */
public class FileUtils {
    /**
     * @param file The file, that should be loaded
     * @return A String array with all lines found in the file.
     * @throws FileNotFoundException Gets thrown, if the file was not found.
     */
    public static String[] loadFile(File file) throws FileNotFoundException {
        ArrayList<String> str = new ArrayList<>();
        Scanner s = new Scanner(file);
        while(s.hasNextLine()) {
            str.add(s.nextLine());
        }
        s.close();
        return str.toArray(new String[] {});
    }

    /**
     * @param file The file where the content should be written to.
     * @param content The content, that should be written to the file. For new lines use "\n"
     * @throws IOException If an I/O error occurs
     */
    public static void saveFile(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }
}

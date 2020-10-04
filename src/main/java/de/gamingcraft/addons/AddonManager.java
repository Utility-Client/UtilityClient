package de.gamingcraft.addons;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class AddonManager {
    public static void start() {
        // Create a File object on the root of the directory containing the class file
        File file = new File("_uc-addons\\");
        file.mkdir();

        try {
            // Convert File to a URL
            URL url = file.toURI().toURL();          // file:/c:/myclasses/
            URL[] urls = new URL[]{url};
            // Create a new class loader with the directory
            ClassLoader cl = new URLClassLoader(urls);
            // Load in the class; MyClass.class should be located in
            // the directory file + addon\Class.class
            Class cls = cl.loadClass("Class");
            ((URLClassLoader)cl).close();

            cls.getDeclaredMethod("init").invoke(cls.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

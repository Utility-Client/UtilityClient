package de.gamingcraft.addons;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Objects;

public class AddonManager {
    private static final ArrayList<Class<?>> classes = new ArrayList<>();

    public static void start() {
        File file = new File("_uc-addons\\");
        if(file.mkdir()) System.out.println("Created _uc-addons directory.");
        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            URLClassLoader cl = new URLClassLoader(urls);
            for (File f : Objects.requireNonNull(file.listFiles())) if(!f.isDirectory()) classes.add(cl.loadClass(f.getName().replace(".class", "")));
            cl.close();
            runAddonEvent("init");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runAddonEvent(String event, Object... args) {
        for (Class<?> c : classes) try { c.getDeclaredMethod(event).invoke(c.newInstance(), args); } catch (Exception e) { e.printStackTrace(); }
    }
}

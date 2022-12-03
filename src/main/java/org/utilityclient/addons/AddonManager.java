package org.utilityclient.addons;

import org.utilityclient.utils.Utils;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AddonManager extends Thread {
    File addonFolder = new File("uc2/addons");
    ArrayList<UC2Addon> addons = new ArrayList<>();

    @Override
    public void run() {
        Utils.ignore(addonFolder.mkdirs(), false);

        for (File addonFile : Objects.requireNonNull(addonFolder.listFiles())) {
            try {
                addons.add((UC2Addon) getClass(addonFile).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (UC2Addon addon : addons) addon.onStartEvent();
    }

    public void loop() {
        for (UC2Addon addon : addons) addon.onUpdateEvent();
    }

    Class<?> getClass(File file) throws IOException, ClassNotFoundException {
        URLClassLoader child = new URLClassLoader(new URL[] { file.toURI().toURL() }, getClass().getClassLoader());
        JarFile jar = new JarFile(file);
        JarEntry je = jar.getJarEntry("addon.uc");
        BufferedReader br = new BufferedReader(new InputStreamReader(jar.getInputStream(je)));
        String className = br.readLine();
        br.close();
        return Class.forName(className, true, child);
    }
}

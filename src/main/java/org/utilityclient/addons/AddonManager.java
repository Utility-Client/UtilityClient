package org.utilityclient.addons;

import org.utilityclient.addons.events.LoopEvent;
import org.utilityclient.utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AddonManager extends Thread {
    private final File addonFolder = new File("uc3/addons");
    private final ArrayList<UC2Addon> legacyAddons = new ArrayList<>();
    private final ArrayList<UC3Addon> addons = new ArrayList<>();
    private static final ArrayList<Event> eventListeners = new ArrayList<>();

    @Override
    public void run() {
        Utils.ignore(addonFolder.mkdirs(), false);

        for (File addonFile : Objects.requireNonNull(addonFolder.listFiles())) {
            try {
                Object newInstance = getClass(addonFile).getDeclaredConstructor().newInstance();
                if (newInstance instanceof UC2Addon) {
                    legacyAddons.add((UC2Addon) newInstance);
                } else if (newInstance instanceof UC3Addon) {
                    addons.add((UC3Addon) newInstance);
                } else {
                    throw new RuntimeException("The addon " + addonFile + " is neither a legacy or a UC³ addon. It won't be loaded.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (UC2Addon addon : legacyAddons) addon.onStartEvent();
    }

    public void loop() {
        for (Event event : eventListeners) {
            if (event instanceof LoopEvent) {
                LoopEvent e = (LoopEvent) event;
                e.onLoop();
            }
        }

        for (UC2Addon addon : legacyAddons) addon.onUpdateEvent();
    }

    public static void registerEvent(Event event) {
        eventListeners.add(event);
    }

    Class<?> getClass(File file) throws IOException, ClassNotFoundException {
        URLClassLoader child = new URLClassLoader(new URL[]{file.toURI().toURL()}, getClass().getClassLoader());
        JarFile jar = new JarFile(file);
        JarEntry je = jar.getJarEntry("addon.uc");
        BufferedReader br = new BufferedReader(new InputStreamReader(jar.getInputStream(je)));
        String className = br.readLine();
        br.close();
        return Class.forName(className, true, child);
    }
}

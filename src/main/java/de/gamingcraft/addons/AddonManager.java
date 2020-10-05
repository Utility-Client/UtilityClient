package de.gamingcraft.addons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class AddonManager {

    private static ArrayList<Class> classes = new ArrayList<Class>();

    public static void start() {
        File file = new File("_uc-addons\\");
        file.mkdir();

        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            ClassLoader cl = new URLClassLoader(urls);


            for (File f : file.listFiles()) {
                if(!f.isDirectory()) {
                    classes.add(cl.loadClass(f.getName().replace(".class", "")));
                }
            }

            ((URLClassLoader)cl).close();


            runAddonEvent("init");

            //runAddonEvent("loop", Minecraft.getMinecraft(), Minecraft.getMinecraft().fontRendererObj, new ScaledResolution(Minecraft.getMinecraft()));

            //runAddonEvent("drawOverlay", Minecraft.getMinecraft(), Minecraft.getMinecraft().fontRendererObj, new ScaledResolution(Minecraft.getMinecraft()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runAddonEvent(String event, Object... args) {
        for (Class c : classes) {
            try {
                c.getDeclaredMethod("init").invoke(c.newInstance(), args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

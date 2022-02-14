package org.utilityclient.addons;

import org.utilityclient.utils.Utils;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AddonManager extends Thread {
    File addonFolder = new File("uc2/addons");

    @Override
    public void run() {
        Utils.ignore(addonFolder.mkdirs(), false);
    }

    Class<?> getClass(File file) throws IOException, ClassNotFoundException {
        URLClassLoader child = new URLClassLoader(new URL[] { file.toURI().toURL() }, getClass().getClassLoader());
        JarFile jar = new JarFile(file);
        JarEntry je = jar.getJarEntry("addon.uc");
        BufferedReader br = new BufferedReader(new InputStreamReader(jar.getInputStream(je)));
        String className;
        while ((className = br.readLine()) != null) Utils.ignore(className, false);
        br.close();
        return Class.forName(className, true, child);
    }
}

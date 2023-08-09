package org.utilityclient.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.utilityclient.api.abstraction.StandaloneCompatible;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@StandaloneCompatible
public class JSONUtils {
    public static Gson gson = new GsonBuilder().generateNonExecutableJson().disableInnerClassSerialization().create();

    public static String downloadJson(String url) {
        try {
            return downloadJson(new URL(url));
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public static String downloadJson(URL url) throws Exception {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder buffer = new StringBuilder();
        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1)
            buffer.append(chars, 0, read);
        reader.close();
        return buffer.toString();
    }

    public static Object parseToJson(String input, java.lang.reflect.Type type) {
        return gson.fromJson(input, type);
    }
}

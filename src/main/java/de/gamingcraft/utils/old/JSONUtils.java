package de.gamingcraft.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

public class JSONUtils {
    /*public static String str = downloadJson();
    public static Repo repo;
    public static String[] changes;

    public static String downloadJson() {
        String urlString = "https://api.github.com/repos/Utility-Client/UtilityClient2/releases/latest";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return urlString;
    }

    public static void parseJson() {
        Gson gson = new GsonBuilder().generateNonExecutableJson().disableInnerClassSerialization().create();
        Type type = new TypeToken<Repo>(){}.getType();
        repo = gson.fromJson(str, type);
        parseOutput();
    }

    public static void parseOutput() {
        String raw = repo.body;
        changes = raw.split("-");
    }*/
}
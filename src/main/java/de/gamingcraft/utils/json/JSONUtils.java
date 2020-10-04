package de.gamingcraft.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONUtils {
    public static String downloadJson(java.net.URL url) {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (reader != null) {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static Object parseToJson(String input, java.lang.reflect.Type type) {
        Gson gson = new GsonBuilder().generateNonExecutableJson().disableInnerClassSerialization().create();
        return gson.fromJson(input, type);
    }
}

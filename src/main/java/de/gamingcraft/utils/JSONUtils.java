package de.gamingcraft.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URL;

public class JSONUtils {

    public static String readUrl(String urlString) throws Exception {
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
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public static String parseJson(String jsonString) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        if(jsonString.startsWith("[")) jsonString = jsonString.substring(1);
        jsonString = jsonString.replace(jsonString.substring(jsonString.length()-1), "");
        //jsonString = jsonString.replaceAll("(?<=\\[)(.*)(?=\\])", "");
        System.out.println(jsonString);
        System.exit(0);

        Root root = gson.fromJson(jsonString, Root.class);

        return root.body;
    }
}
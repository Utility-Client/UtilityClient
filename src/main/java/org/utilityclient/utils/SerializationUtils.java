package org.utilityclient.utils;

import java.io.*;
import java.util.Base64;

/**
 * Utilities for (De-)Serialization of Java Objects.
 * @author GamingCraft
 * @since 2.9.1
 */
public class SerializationUtils {
    /**
     * Serializes a Serializable into a String, that can be stored or deserialized into an Object again.
     * @param o The Serializable, that should be serialized.
     * @return A string, that can be easier stored.
     * @throws IOException If an I/O error occurs
     */
    public static String serialize(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    /**
     * Convert a string back into an object.
     * @param s The input string
     * @return An object
     * @throws IOException If an I/O error occurs
     * @throws ClassNotFoundException Class of a serialized object cannot be found.
     */
    public static Object deserialize(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }
}

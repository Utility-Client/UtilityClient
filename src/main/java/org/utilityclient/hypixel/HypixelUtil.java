package org.utilityclient.hypixel;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;
import java.util.UUID;

public class HypixelUtil {
    public static final HypixelAPI API;

    static {
        String key = System.getProperty("apiKey", "9e45e98c-4cc4-409a-a819-cc4fb9f33c2d");
        API = new HypixelAPI(new ApacheHttpClient(UUID.fromString(key)));
    }
}

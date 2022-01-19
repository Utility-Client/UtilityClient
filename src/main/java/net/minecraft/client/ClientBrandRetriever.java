package net.minecraft.client;

import org.utilityclient.UtilityClient;

public class ClientBrandRetriever
{
    public static String getClientModName()
    {
        return UtilityClient.getVersion();
    }
}

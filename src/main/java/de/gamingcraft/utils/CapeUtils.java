package de.gamingcraft.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CapeUtils extends Thread {

    public ResourceLocation ofLocationCape = null;
    public String rawCapesJson = "";
    public ArrayList<CapeOwner> rootJson = null;

    @Override
    public void run() {
        rawCapesJson = downloadJson();
        parseJson();

        super.run();

    }

    public String downloadJson() {
        String urlString = "http://api.gamingcraft.de/capes/index.json";
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

    public void parseJson() {
        Gson gson = new GsonBuilder().generateNonExecutableJson().disableInnerClassSerialization().create();
        Type collectionType = new TypeToken<Collection<CapeOwner>>(){}.getType();
        rootJson = gson.fromJson(rawCapesJson, collectionType);
    }

    public void downloadCape(String username) {
        if (username != null && !username.isEmpty()) {
            username = StringUtils.stripControlCodes(username);
            String filename = "";

            for (CapeOwner cOwner : rootJson) {
                if(cOwner.username.equalsIgnoreCase(username)) {
                    filename = cOwner.filename;
                }
            }
            
            String ofCapeUrl = "http://api.gamingcraft.de/capes/" + filename + ".png";

            MinecraftProfileTexture mpt = new MinecraftProfileTexture(ofCapeUrl, new HashMap());
            final ResourceLocation rl = new ResourceLocation("skins/" + mpt.getHash());
            IImageBuffer iib = new IImageBuffer() {
                ImageBufferDownload ibd = new ImageBufferDownload();

                public BufferedImage parseUserSkin(BufferedImage var1) {
                    return parseCape(var1);
                }

                public void skinAvailable() {
                    ofLocationCape = rl;
                }
            };
            ThreadDownloadImageData textureCape = new ThreadDownloadImageData((File) null, mpt.getUrl(), (ResourceLocation) null, iib);
            Minecraft.getMinecraft().getTextureManager().loadTexture(rl, textureCape);
        }
    }

    private BufferedImage parseCape(BufferedImage img) {
        int imageWidth = 64;
        int imageHeight = 32;
        int srcWidth = img.getWidth();

        for (int srcHeight = img.getHeight(); imageWidth < srcWidth || imageHeight < srcHeight; imageHeight *= 2) {
            imageWidth *= 2;
        }

        BufferedImage imgNew = new BufferedImage(imageWidth, imageHeight, 2);
        Graphics g = imgNew.getGraphics();
        g.drawImage(img, 0, 0, (ImageObserver) null);
        g.dispose();
        return imgNew;
    }
}

package de.gamingcraft.utils.json;

import com.mojang.authlib.minecraft.*;
import com.sun.istack.internal.NotNull;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.HashMap;

public class CapeUtils {

    public ResourceLocation ucLocationCape;

    public void downloadCape(@NotNull String url, @NotNull String filename) {
        String ucCapeUrl = url + filename;

        MinecraftProfileTexture mpt = new MinecraftProfileTexture(ucCapeUrl, new HashMap());
        final ResourceLocation rl = new ResourceLocation("skins/" + mpt.getHash());
        IImageBuffer iib = new IImageBuffer() {
            ImageBufferDownload ibd = new ImageBufferDownload();

            public BufferedImage parseUserSkin(BufferedImage var1) {
                return parseCape(var1);
            }

            public void skinAvailable() {
                ucLocationCape = rl;
            }
        };
        ThreadDownloadImageData textureCape = new ThreadDownloadImageData((File) null, mpt.getUrl(), (ResourceLocation) null, iib);
        Minecraft.getMinecraft().getTextureManager().loadTexture(rl, textureCape);
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

package org.utilityclient.utils.json;

import java.awt.*;
import java.awt.image.*;

public class CapeUtils {
    //public ResourceLocation ucLocationCape;

    public void downloadCape(String url, String filename) {
        /*String ucCapeUrl = url + filename + ".png";
        MinecraftProfileTexture mpt = new MinecraftProfileTexture(ucCapeUrl, new HashMap<>());
        final ResourceLocation rl = new ResourceLocation("skins/" + mpt.getHash());
        IImageBuffer iib = new IImageBuffer() {
            public BufferedImage parseUserSkin(BufferedImage var1) {
                return parseCape(var1);
            }
            public void skinAvailable() {
                ucLocationCape = rl;
            }
        };
        ThreadDownloadImageData textureCape = new ThreadDownloadImageData(null, ucCapeUrl, null, iib);
        Minecraft.getMinecraft().getTextureManager().loadTexture(rl, textureCape);*/
    }

    private BufferedImage parseCape(BufferedImage img) {
        int imageWidth = 64;
        int imageHeight = 32;
        int srcWidth = img.getWidth();
        for (int srcHeight = img.getHeight(); imageWidth < srcWidth || imageHeight < srcHeight; imageHeight *= 2) imageWidth *= 2;
        BufferedImage imgNew = new BufferedImage(imageWidth, imageHeight, 2);
        Graphics g = imgNew.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return imgNew;
    }
}

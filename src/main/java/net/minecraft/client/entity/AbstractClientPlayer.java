package net.minecraft.client.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import de.gamingcraft.UtilityClient;
import de.gamingcraft.utils.json.CapeUtils;
import de.gamingcraft.utils.json.JSONUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import java.net.URL;

public abstract class AbstractClientPlayer extends EntityPlayer {
    private final CapeUtils capeUtils = new CapeUtils();
    private NetworkPlayerInfo playerInfo;
    private final JsonArray capeIndex = JSONUtils.gson.fromJson(JSONUtils.downloadJson("http://cdn.gamingcraft.de/uclient/index.json"), JsonArray.class);

    public AbstractClientPlayer(World worldIn) {
        super(worldIn, null);
    }

    public AbstractClientPlayer(World worldIn, GameProfile playerProfile) {
        super(worldIn, playerProfile);
        try {
            String name = playerProfile.getName();
            if (org.apache.commons.lang3.StringUtils.isBlank(name))
                name = JSONUtils.gson.fromJson(JSONUtils.downloadJson(new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + playerProfile.getId())), JsonObject.class).get("name").getAsString();

            if(!UtilityClient.capesEnabled) return;
            for (JsonElement capeOwners : JSONUtils.gson.fromJson(capeIndex, JsonArray.class)) {
                String finalName = name;
                capeOwners.getAsJsonObject().get("usernames").getAsJsonArray().forEach(username -> {
                    if(username.getAsString().equalsIgnoreCase(finalName))
                        capeUtils.downloadCape("http://cdn.gamingcraft.de/uclient/", capeOwners.getAsJsonObject().get("file").getAsString());
                });
            }
        } catch (Exception e) {
            System.err.println("Please send this error to the UtilityClient developers:");
            System.err.println("--- START OF ERROR ---");
            System.err.println(e);
            System.err.println("--- END OF ERROR ---");
        }
    }

    public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation resourceLocationIn, String username) {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject itextureobject = texturemanager.getTexture(resourceLocationIn);

        if (itextureobject == null) {
            itextureobject = new ThreadDownloadImageData(null, String.format("https://skins.minecraft.net/MinecraftSkins/%s.png", StringUtils.stripControlCodes(username)), DefaultPlayerSkin.getDefaultSkin(getOfflineUUID(username)), new ImageBufferDownload());
            texturemanager.loadTexture(resourceLocationIn, itextureobject);
        }

        return (ThreadDownloadImageData) itextureobject;
    }

    /**
     * Returns true if the username has an associated skin.
     */
    public static ResourceLocation getLocationSkin(String username) {
        return new ResourceLocation("skins/" + StringUtils.stripControlCodes(username));
    }

    /**
     * Returns true if the player is in spectator mode.
     */
    public boolean isSpectator() {
        NetworkPlayerInfo networkplayerinfo = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(this.getGameProfile().getId());
        return networkplayerinfo != null && networkplayerinfo.getGameType() == WorldSettings.GameType.SPECTATOR;
    }

    /**
     * Checks if this instance of AbstractClientPlayer has any associated player data.
     */
    public boolean hasPlayerInfo() {
        return this.getPlayerInfo() != null;
    }

    protected NetworkPlayerInfo getPlayerInfo() {
        if (this.playerInfo == null) {
            this.playerInfo = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(this.getUniqueID());
        }

        return this.playerInfo;
    }

    /**
     * Returns true if the player has an associated skin.
     */
    public boolean hasSkin() {
        NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
        return networkplayerinfo != null && networkplayerinfo.hasLocationSkin();
    }

    /**
     * Returns true if the player instance has an associated skin.
     */
    public ResourceLocation getLocationSkin() {
        NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
        return networkplayerinfo == null ? DefaultPlayerSkin.getDefaultSkin(this.getUniqueID()) : networkplayerinfo.getLocationSkin();
    }

    public ResourceLocation getLocationCape() {
        if (capeUtils.ucLocationCape != null) {
            // Capes
            return capeUtils.ucLocationCape;
        } else {
            NetworkPlayerInfo var1 = this.getPlayerInfo();
            return var1 == null ? null : var1.getLocationCape();
        }
    }

    public String getSkinType() {
        NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
        return networkplayerinfo == null ? DefaultPlayerSkin.getSkinType(this.getUniqueID()) : networkplayerinfo.getSkinType();
    }

    public float getFovModifier() {
        float f = 1.0F;
        if (this.capabilities.isFlying) f *= 1.1F;

        IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        f = (float) ((double) f * ((iattributeinstance.getAttributeValue() / (double) this.capabilities.getWalkSpeed() + 1.0D) / 2.0D));

        if (this.capabilities.getWalkSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f)) {
            f = 1.0F;
        }

        if (this.isUsingItem() && this.getItemInUse().getItem() == Items.bow) {
            int i = this.getItemInUseDuration();
            float f1 = (float) i / 20.0F;

            if (f1 > 1.0F) {
                f1 = 1.0F;
            } else {
                f1 = f1 * f1;
            }

            f *= 1.0F - f1 * 0.15F;
        }

        return f * UtilityClient.fovModifier;
    }

}

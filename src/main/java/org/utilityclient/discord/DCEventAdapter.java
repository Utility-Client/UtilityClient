package org.utilityclient.discord;

import de.jcm.discordgamesdk.DiscordEventAdapter;
import de.jcm.discordgamesdk.user.Relationship;
import de.jcm.discordgamesdk.user.RelationshipType;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;

public class DCEventAdapter extends DiscordEventAdapter {

    @Override
    public void onRelationshipUpdate(Relationship relationship) {
        super.onRelationshipUpdate(relationship);
        if(relationship.getType() != RelationshipType.FRIEND) return;
        if (UtilityClient.streamerMode) return;
        if(!Config.getBoolean(ConfigEntry.DISCORD_FRIEND_NOTIFICATIONS)) return;

        switch (relationship.getPresence().getStatus()) {
            case OFFLINE -> Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Discord » " + EnumChatFormatting.GRAY + relationship.getUser().getUsername() + " went offline."));
            case ONLINE -> Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Discord » " + EnumChatFormatting.GREEN + relationship.getUser().getUsername() + " went online."));
            case IDLE -> Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Discord » " + EnumChatFormatting.YELLOW + relationship.getUser().getUsername() + " is idle now."));
            case DO_NO_DISTURB -> Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Discord » " + EnumChatFormatting.RED + relationship.getUser().getUsername() + " is busy now."));
        }
    }
}

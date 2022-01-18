package org.utilityclient.discord;

import de.jcm.discordgamesdk.DiscordEventAdapter;
import de.jcm.discordgamesdk.user.OnlineStatus;
import de.jcm.discordgamesdk.user.Relationship;
import de.jcm.discordgamesdk.user.RelationshipType;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class DCEventAdapter extends DiscordEventAdapter {
    @Override
    public void onRelationshipUpdate(Relationship relationship) {
        super.onRelationshipUpdate(relationship);
        if(relationship.getType() != RelationshipType.FRIEND) return;

        if (relationship.getPresence().getStatus() == OnlineStatus.OFFLINE) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Discord » " + EnumChatFormatting.RED + relationship.getUser().getUsername() + " went offline."));
        } else if (relationship.getPresence().getStatus() == OnlineStatus.ONLINE) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Discord » " + EnumChatFormatting.GREEN + relationship.getUser().getUsername() + " went online."));
        }
    }
}

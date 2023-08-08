package org.utilityclient.discord;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.jcm.discordgamesdk.DiscordEventAdapter;
import de.jcm.discordgamesdk.user.Relationship;
import de.jcm.discordgamesdk.user.RelationshipType;
import org.utilityclient.UtilityClient;
import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;

@StandaloneCompatible
public class DCEventAdapter extends DiscordEventAdapter {
    @Override
    public void onRelationshipUpdate(Relationship relationship) {
        super.onRelationshipUpdate(relationship);
        if(relationship.getType() != RelationshipType.FRIEND) return;
        if (UtilityClient.streamerMode) return;
        if(!Config.getBoolean(ConfigEntry.DISCORD_FRIEND_NOTIFICATIONS)) return;

        switch (relationship.getPresence().getStatus()) {
            case OFFLINE: UtilityClient.getInstance().wrapper.writeChatMessage(ChatFormatting.AQUA + "Discord » " + ChatFormatting.GRAY + relationship.getUser().getUsername() + " went offline."); break;
            case ONLINE: UtilityClient.getInstance().wrapper.writeChatMessage(ChatFormatting.AQUA + "Discord » " + ChatFormatting.GREEN + relationship.getUser().getUsername() + " went online."); break;
            case IDLE: UtilityClient.getInstance().wrapper.writeChatMessage(ChatFormatting.AQUA + "Discord » " + ChatFormatting.YELLOW + relationship.getUser().getUsername() + " is idle now."); break;
            case DO_NO_DISTURB: UtilityClient.getInstance().wrapper.writeChatMessage(ChatFormatting.AQUA + "Discord » " + ChatFormatting.RED + relationship.getUser().getUsername() + " is busy now."); break;
        }
    }
}

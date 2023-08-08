package org.utilityclient.discord;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.jcm.discordgamesdk.DiscordEventAdapter;
import de.jcm.discordgamesdk.user.Relationship;
import de.jcm.discordgamesdk.user.RelationshipType;
import net.minecraft.text.LiteralText;
import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.utils.Utils;

public class DCEventAdapter extends DiscordEventAdapter {
    @Override
    public void onRelationshipUpdate(Relationship relationship) {
        super.onRelationshipUpdate(relationship);
        if(relationship.getType() != RelationshipType.FRIEND) return;
        if (UtilityClient.streamerMode) return;
        if(!Config.getBoolean(ConfigEntry.DISCORD_FRIEND_NOTIFICATIONS)) return;

        switch (relationship.getPresence().getStatus()) {
            case OFFLINE: Utils.chat(new LiteralText(ChatFormatting.AQUA + "Discord » " + ChatFormatting.GRAY + relationship.getUser().getUsername() + " went offline."));break;
            case ONLINE: Utils.chat(new LiteralText(ChatFormatting.AQUA + "Discord » " + ChatFormatting.GREEN + relationship.getUser().getUsername() + " went online."));break;
            case IDLE: Utils.chat(new LiteralText(ChatFormatting.AQUA + "Discord » " + ChatFormatting.YELLOW + relationship.getUser().getUsername() + " is idle now."));break;
            case DO_NO_DISTURB: Utils.chat(new LiteralText(ChatFormatting.AQUA + "Discord » " + ChatFormatting.RED + relationship.getUser().getUsername() + " is busy now."));break;
        }
    }
}

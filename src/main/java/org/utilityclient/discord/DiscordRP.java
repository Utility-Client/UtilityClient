package org.utilityclient.discord;

import org.utilityclient.UtilityClient;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;

public class DiscordRP extends Thread {

    private static boolean shouldRun = false;

    @Override
    public void run() {

        super.run();

        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(user -> System.out.println("Discord Hook: Welcome " + user.username + "#" + user.discriminator + "!")).build();
        DiscordRPC.discordInitialize(UtilityClient.getDiscordApplicationId(), handlers, true);
        shouldRun = true;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Discord Hook: Closing Discord hook.");
            DiscordRPC.discordShutdown();
        }));
    }

    public void loop() {

        if(!shouldRun) return;

        String topText = "Idle";
        String bottomText = "";

        if(Minecraft.getMinecraft().isSingleplayer()) {
            topText = "Playing Singleplayer";
        } else if(Minecraft.getMinecraft().theWorld != null) {
            if(Minecraft.getMinecraft().theWorld.playerEntities.size() > 1) {
                topText = "Playing Multiplayer";
            }
        }

        if(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) {
            topText = "In Menus";
            bottomText = "Main Menu";
        }

        if(Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer) {
            topText = "In Menus";
            bottomText = "Server List";
        }

        if(Minecraft.getMinecraft().currentScreen instanceof GuiSelectWorld) {
            topText = "In Menus";
            bottomText = "World List";
        }

        setRichPresence(topText, bottomText);
        DiscordRPC.discordRunCallbacks();
    }

    public void setRichPresence(String topText, String bottomText) {
        DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder(bottomText);
        presence.setBigImage("utilityclient", UtilityClient.getClientName() + " " + UtilityClient.getVersion());
        presence.setDetails(topText);
        DiscordRPC.discordUpdatePresence(presence.build());
    }
}

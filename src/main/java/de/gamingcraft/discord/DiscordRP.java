package de.gamingcraft.discord;

import de.gamingcraft.UtilityClient;
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


        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            System.out.println("[--:--:--] [DiscordWebhook/INFO]: Welcome " + user.username + "#" + user.discriminator + "!");
        }).build();
        DiscordRPC.discordInitialize("742760119984455701", handlers, true);
        shouldRun = true;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Closing Discord hook.");
            DiscordRPC.discordShutdown();
        }));
    }

    public void loop() {

        if(!shouldRun) return;

        if(Minecraft.getMinecraft().isSingleplayer()) {
            setRichPresence("Playing Singleplayer", "");
        }

        if((!Minecraft.getMinecraft().isSingleplayer())) {
            if(Minecraft.getMinecraft().theWorld != null) {
                if(Minecraft.getMinecraft().theWorld.playerEntities.size() > 1) {
                    setRichPresence("Playing Multiplayer", Minecraft.getMinecraft().getCurrentServerData().serverIP);
                }
            }
        }

        if(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) {
            setRichPresence("In Menus", "Main Menu");
        }

        if(Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer) {
            setRichPresence("In Menus", "Server List");
        }

        if(Minecraft.getMinecraft().currentScreen instanceof GuiSelectWorld) {
            setRichPresence("In Menus", "World List");
        }

        DiscordRPC.discordRunCallbacks();
    }

    public void setRichPresence(String topText, String bottomText) {
        DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder(bottomText);
        presence.setBigImage("utilityclient", UtilityClient.getName() + " " + UtilityClient.getVersion());
        presence.setDetails(topText);
        DiscordRPC.discordUpdatePresence(presence.build());
    }
}

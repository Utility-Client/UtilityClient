package de.gamingcraft.discord;

import de.gamingcraft.UtilityClient;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Discord extends Thread {

    private static final Logger logger = LogManager.getLogger();
    private boolean shouldRun = true;

    public Discord() {
        setDaemon(false);
        setName("Discord-Thread");
    }

    @Override
    public void run() {
        super.run();
        startup();
    }


    public void startup(){
        logger.info("Loading Discord RPC...");
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            logger.info("Discord RPC finished loading.");
            logger.info("Welcome " + user.username + "#" + user.discriminator + ".");
            DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder("Idle");
            presence.setDetails("In Main Menu");
            presence.setBigImage("utilityclient", UtilityClient.getName() + " " + UtilityClient.getVersion());
            DiscordRPC.discordUpdatePresence(presence.build());
        }).build();
        DiscordRPC.discordInitialize("742760119984455701", handlers, false);
        DiscordRPC.discordRegister("742760119984455701", "");

        while (shouldRun) {
            loop();
        }
    }

    public void shutdown() {
        shouldRun = false;
        DiscordRPC.discordShutdown();
    }

    public void loop() {
        DiscordRPC.discordRunCallbacks();
    }

    public void onScreenChangeEvent() {
        /*if(Minecraft.getMinecraft().isSingleplayer()) {
            updatePresence(false, "In Singleplayer", true);
        }else if(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu || Minecraft.getMinecraft().currentScreen instanceof GuiSelectWorld || Minecraft.getMinecraft().currentScreen instanceof GuiScreenServerList) {
            updatePresence(true, "In Menu", false);
        }else if(!Minecraft.getMinecraft().isSingleplayer()) {
            updatePresence(false, "Playing on " + Minecraft.getMinecraft().getCurrentServerData().serverIP, false);
        }*/
    }

    public static void updatePresence(boolean isMenu, String description, boolean isSingleplayer) {
        DiscordRichPresence.Builder presence;

        if(isMenu) presence = new DiscordRichPresence.Builder("Idle"); else presence = new DiscordRichPresence.Builder("Playing");

        if(isSingleplayer) {
            presence.setSmallImage("singleplayer", "Singleplayer");
        }

        presence.setBigImage("utilityclient", UtilityClient.getName() + " " + UtilityClient.getVersion());

        presence.setDetails(description);

        DiscordRPC.discordUpdatePresence(presence.build());
    }
}

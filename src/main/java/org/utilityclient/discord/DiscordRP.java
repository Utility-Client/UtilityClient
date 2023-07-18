package org.utilityclient.discord;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import org.apache.logging.log4j.LogManager;
import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.gui.UCScreen;
import org.utilityclient.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Sam302
 * @author Niklas-Dev
 * @since 2.0 LTS
 */
public class DiscordRP extends Thread {

    private static boolean shouldRun = false;
    public static Core core;
    private String oldTopText, oldBottomText;

    public DiscordRP() {
        this.setName("Discord rich presence");
        this.setPriority(MIN_PRIORITY); // It's just the Discord RP. (Almost) no one cares, if it even works.
    }

    @Override
    public void run() {
        super.run();

        try {
            Core.init(Objects.requireNonNull(downloadDiscordLibrary()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        CreateParams params = new CreateParams();
        params.setClientID(UtilityClient.getDiscordApplicationId());
        params.setFlags(CreateParams.Flags.DEFAULT);
        params.registerEventHandler(new DCEventAdapter());
        core = new Core(params);
        shouldRun = true;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            shouldRun = false;
            LogManager.getLogger().info("Discord Hook: Closing Discord hook.");
            core.close();
        }));


        while (shouldRun) {
            try {
                Thread.sleep(500);
                loop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void loop() {
        if(!Config.getBoolean(ConfigEntry.DISCORD_RICH_PRESENCE)) return;
        if(!shouldRun) return;

        String topText = "";
        String bottomText = "";

        try {
            if (MinecraftClient.getInstance().isInSingleplayer()) {
                topText = "Playing Singleplayer";
            } else if (MinecraftClient.getInstance().getCurrentServerEntry() != null) {
                topText = "Playing Multiplayer";
                if (Config.getBoolean(ConfigEntry.DISCORD_SHOW_SERVER))
                    bottomText = "Current Server: " + MinecraftClient.getInstance().getCurrentServerEntry().address;
            }
        } catch (Exception e) {
            Utils.ignore(e);
        }

        if (MinecraftClient.getInstance().currentScreen instanceof UCScreen) {
            UCScreen currentScreen = (UCScreen) MinecraftClient.getInstance().currentScreen;
            topText = currentScreen.getActionLabel();
            bottomText = "";
        }

        if (MinecraftClient.getInstance().currentScreen instanceof SelectWorldScreen) {
            topText = "Choosing a world to play";
            bottomText = "";
        }

        // Prevent unneeded updates.
        // This should improve performance and reduce bandwidth.
        // Since 2.15 LTS.
        if(!(topText.equalsIgnoreCase(oldTopText) && bottomText.equalsIgnoreCase(oldBottomText))) {
            setRichPresence(topText, bottomText);
            oldTopText = topText;
            oldBottomText = bottomText;
        }

        core.runCallbacks();
    }

    public void setRichPresence(String topText, String bottomText) {
        Activity activity = new Activity();
        activity.setDetails(topText);
        activity.setState(bottomText);
        activity.assets().setLargeImage("rebrand");
        activity.assets().setLargeText(UtilityClient.getClientName() + " " + UtilityClient.getVersion());
        core.activityManager().updateActivity(activity);
    }

    /**
     * Downloads the discord library
     *
     * @return The library as file
     * @throws IOException Something went wrong!
     * @author Example by @JnCrMx
     */
    public static File downloadDiscordLibrary() throws IOException {
        // Find out which name Discord's library has (.dll for Windows, .so for Linux)
        String name = "discord_game_sdk";
        String suffix;

        String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String arch = System.getProperty("os.arch").toLowerCase(Locale.ROOT);

        if (osName.contains("windows")) suffix = ".dll";
        else if (osName.contains("linux")) suffix = ".so";
        else if (osName.contains("mac os")) suffix = ".dylib";
        else throw new RuntimeException("cannot determine OS type: " + osName);

		/*
		Some systems report "amd64" (e.g. Windows and Linux), some "x86_64" (e.g. macOS).
		At this point we need the "x86_64" version, as this one is used in the ZIP.
		 */
        if (arch.equals("amd64")) arch = "x86_64";

        // Path of Discord's library inside the ZIP
        String zipPath = "lib/" + arch + "/" + name +suffix;

        // Open the URL as a ZipInputStream
        URL downloadUrl = new URL("https://dl-game-sdk.discordapp.net/2.5.6/discord_game_sdk.zip");
        ZipInputStream zin = new ZipInputStream(downloadUrl.openStream());

        // Search for the right file inside the ZIP
        ZipEntry entry;
        while((entry = zin.getNextEntry())!=null)
        {
            if(entry.getName().equals(zipPath))
            {
                // Create a new temporary directory
                // We need to do this, because we may not change the filename on Windows
                File tempDir = new File(System.getProperty("java.io.tmpdir"), "java-"+name+System.nanoTime());
                if (!tempDir.mkdir()) throw new IOException("Cannot create temporary directory");
                tempDir.deleteOnExit();

                // Create a temporary file inside our directory (with a "normal" name)
                File temp = new File(tempDir, name+suffix);
                temp.deleteOnExit();

                // Copy the file in the ZIP to our temporary file
                Files.copy(zin, temp.toPath());

                // We are done, so close the input stream
                zin.close();

                // Return our temporary file
                return temp;
            }
            // next entry
            zin.closeEntry();
        }
        zin.close();
        // We couldn't find the library inside the ZIP
        return null;
    }
}

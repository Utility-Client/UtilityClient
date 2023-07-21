package org.utilityclient.gui.overrides;

import com.google.gson.reflect.TypeToken;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.options.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DemoServerWorld;
import net.minecraft.world.level.LevelProperties;
import net.minecraft.world.level.storage.LevelStorageAccess;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GLContext;
import org.utilityclient.UtilityClient;
import org.utilityclient.config.Config;
import org.utilityclient.config.ConfigEntry;
import org.utilityclient.gui.UCScreen;
import org.utilityclient.utils.Color;
import org.utilityclient.utils.Utils;
import org.utilityclient.utils.json.JSONUtils;
import org.utilityclient.utils.json.objects.Release;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.time.LocalDateTime;

public class GuiMainMenu extends UCScreen {
    private static final Logger logger = LogManager.getLogger();
    private final Object threadLock = new Object();
    private String openGLWarning1;
    private String openGLWarning2;
    private String openGLWarningLink;
    private static final Identifier minecraftTitleTextures = new Identifier("textures/gui/title/minecraft.png");
    public static final String openGLWarning = "Please click " + ChatFormatting.UNDERLINE + "here" + ChatFormatting.RESET + " for more information.";
    private int field_92024_r;
    private int field_92022_t;
    private int field_92021_u;
    private int field_92020_v;
    private int field_92019_w;
    private final Release release;
    private boolean isLatest;
    private boolean shouldShowChangelog = Config.getBoolean(ConfigEntry.SHOW_CHANGELOG_IN_TITLE_SCREEN);
    private ButtonWidget toggleChangelogBtn;

    public GuiMainMenu() {
        super("On the title screen");

        openGLWarning2 = openGLWarning;
        openGLWarning1 = "";

        if (!GLContext.getCapabilities().OpenGL20) {
            openGLWarning1 = I18n.translate("title.oldgl1");
            openGLWarning2 = I18n.translate("title.oldgl2");
            openGLWarningLink = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }

        String rawJsonRelease = JSONUtils.downloadJson("https://api.github.com/repos/Utility-Client/UtilityClient/releases/latest");
        release = (Release) JSONUtils.parseToJson(rawJsonRelease, new TypeToken<Release>() {
        }.getType());
        assert release != null;
        isLatest = release.tag_name.contains(UtilityClient.getVersion());
        isLatest = isLatest || UtilityClient.getVersion().contains("-");
    }

    public void init() {
        int j = height / 2 - 32;

        int offset = -(height / 4);
        buttons.add(new ButtonWidget(1, width / 2 - 100 + offset, j, I18n.translate("menu.singleplayer")));
        buttons.add(new ButtonWidget(2, width / 2 - 100 + offset, j + 24, I18n.translate("menu.multiplayer")));
        buttons.add(new ButtonWidget(0, width / 2 - 100 + offset, j + 24 * 2, I18n.translate("menu.options")));
        buttons.add(new ButtonWidget(4, width / 2 - 100 + offset, j + 24 * 3, I18n.translate("menu.quit")));

        ButtonWidget updateBtn = new ButtonWidget(9, width - 102, height - 22, 100, 20, "Install Update");
        updateBtn.active = !isLatest;
        buttons.add(updateBtn);

        toggleChangelogBtn = new ButtonWidget(10, width - 126, height - 22, 20, 20, ">>");
        buttons.add(toggleChangelogBtn);

        synchronized (threadLock) {
            field_92024_r = textRenderer.getStringWidth(openGLWarning2);
            int k = Math.max(textRenderer.getStringWidth(openGLWarning1), field_92024_r);
            field_92022_t = (width - k) / 2;
            field_92021_u = buttons.get(0).y - 24;
            field_92020_v = field_92022_t + k;
            field_92019_w = field_92021_u + 24;
        }

        client.setConnectedToRealms(false);
    }

    protected void buttonClicked(ButtonWidget button) {
        if (button.id == 0) client.openScreen(new SettingsScreen(this, client.options));
        if (button.id == 1) client.openScreen(new SelectWorldScreen(this));
        if (button.id == 2) client.openScreen(new MultiplayerScreen(this));
        if (button.id == 4) client.scheduleStop();
        if (button.id == 5)
            client.openScreen(new LanguageOptionsScreen(this, client.options, client.getLanguageManager()));
        if (button.id == 9) try {
            InputStream initialStream = UtilityClient.class.getClassLoader().getResourceAsStream("updater.jar");
            File targetFile = new File("versions/1.8.8-UtilityClient/updater.jar");
            OutputStream outStream = Files.newOutputStream(targetFile.toPath());
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while (true) {
                assert initialStream != null;
                if ((bytesRead = initialStream.read(buffer)) == -1) break;
                outStream.write(buffer, 0, bytesRead);
            }
            IOUtils.closeQuietly(initialStream);
            IOUtils.closeQuietly(outStream);
            // TODO: Replace "java" with ProcessHandle.current() when upgrading to newer Java version OR do the same with a Java 8 method.
            Runtime.getRuntime().exec("java -jar " + new File("versions/1.8.8-UtilityClient/updater.jar").getAbsolutePath(), null, new File("versions/1.8.8-UtilityClient"));
            MinecraftClient.getInstance().scheduleStop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (button.id == 10) {
            toggleChangelogBtn.message = shouldShowChangelog ? "<<" : ">>";
            shouldShowChangelog = !shouldShowChangelog;
            Config.setBoolean(ConfigEntry.SHOW_CHANGELOG_IN_TITLE_SCREEN, shouldShowChangelog);
        }

        if (button.id == 11) client.startGame("Demo_World", "Demo_World", DemoServerWorld.INFO);

        if (button.id == 12) {
            LevelStorageAccess lsa = client.getCurrentSave();
            LevelProperties lp = lsa.getLevelProperties("Demo_World");

            if (lp != null) {
                ConfirmScreen confirmScreen = SelectWorldScreen.createDeleteWarningScreen(this, lp.getLevelName(), 12);
                client.openScreen(confirmScreen);
            }
        }
    }

    public void confirmResult(boolean result, int id) {
        if (result && id == 12) {
            LevelStorageAccess lsa = client.getCurrentSave();
            lsa.method_254();
            lsa.deleteLevel("Demo_World");
            client.openScreen(this);
        } else if (id == 13) {
            if (result) {
                try {
                    Class<?> oclass = Class.forName("java.awt.Desktop");
                    Object object = oclass.getMethod("getDesktop").invoke(null);
                    oclass.getMethod("browse", URI.class).invoke(object, new URI(openGLWarningLink));
                } catch (Throwable throwable) {
                    logger.error("Couldn't open link", throwable);
                }
            }
            client.openScreen(this);
        }
    }

    int _longest = 0;

    public void render(int mouseX, int mouseY, float partialTicks) {
        drawUtilityClientBackground();
        GlStateManager.enableAlphaTest();
        int i = 274;
        int j = width / 2 - i / 2 - (height / 4);
        int k = 72;
        client.getTextureManager().bindTexture(minecraftTitleTextures);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        int sg = 10;
        DrawableHelper dh = new DrawableHelper();
        dh.drawTexture(j, k, 0, 0, 155 + sg, 44);
        dh.drawTexture(j + 155, k, 0, 45, 155, 44);

        GlStateManager.pushMatrix();
        GlStateManager.translatef((float) (width / 4), 70.0F, 0.0F);
        GlStateManager.rotatef(-20.0F, 0.0F, 0.0F, 1.0F);
        float f = 1.8F - MathHelper.abs(MathHelper.sin((float) (MinecraftClient.getTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
        GlStateManager.scalef(f, f, f);
        GlStateManager.popMatrix();

        if (shouldShowChangelog) try {
            String[] changes = release.body.split("-");
            if (_longest != 0) fill(_longest - 8, 0, width, height, Color.BACKGROUND.color);
            int center = (changes.length * 10) / 2, y = (width - _longest) / 2 - textRenderer.getStringWidth(release.name + " - " + release.tag_name) / 2;
            if (_longest != 0)
                textRenderer.drawWithShadow(release.name + " - " + release.tag_name, _longest + y, height / 2f - center - 10, -1);
            int index = 0, longest = 0;
            for (String str : changes) {
                str = str.replace("\r", "").replace("\n", "");
                if (_longest != 0)
                    textRenderer.drawWithShadow(str, _longest - 2, height / 2f - center + (index * 10), -1);
                int length = textRenderer.getStringWidth(str);
                if (length > longest) longest = length;
                index++;
            }
            _longest = width - longest - 4;
        } catch (Exception ignored) {
            textRenderer.draw("The changelog was disabled due to GitHub's rate limit.", 4, 4, -1);
        }

        if (openGLWarning1 != null && openGLWarning1.length() > 0) {
            fill(field_92022_t - 2, field_92021_u - 2, field_92020_v + 2, field_92019_w - 1, 1426063360);
            textRenderer.draw(openGLWarning1, field_92022_t, field_92021_u, -1);
            textRenderer.draw(openGLWarning2, (width - field_92024_r) / 2, buttons.get(0).y - 12, -1);
        }

        super.render(mouseX, mouseY, partialTicks);
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void drawUtilityClientBackground() {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        renderBackground(); // TODO: Get the UtilityClient Background to render again.
        // client.getTextureManager().bindTexture(Utils.getSeasonOfMonth(LocalDateTime.now().getMonthValue()).getIdentifier());
        // NOTE: Gui => DrawableHelper
        // DrawableHelper.drawTexture(0, 0, 0, 0, width, height, width, height, width, height);
    }
}


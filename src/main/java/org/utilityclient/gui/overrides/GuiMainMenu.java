package org.utilityclient.gui.overrides;

import com.google.gson.reflect.TypeToken;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GLContext;
import org.utilityclient.utils.Color;
import org.utilityclient.utils.json.JSONUtils;
import org.utilityclient.utils.json.objects.Release;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class GuiMainMenu extends Screen
{
    private static final Logger logger = LogManager.getLogger();
    private final Object threadLock = new Object();
    private String openGLWarning1;
    private String openGLWarning2;
    private String openGLWarningLink;
    private static final Identifier minecraftTitleTextures = new Identifier("textures/gui/title/minecraft.png");
    public static final String field_96138_a = "Please click " + ChatFormatting.UNDERLINE + "here" + ChatFormatting.RESET + " for more information.";
    private int field_92024_r;
    private int field_92022_t;
    private int field_92021_u;
    private int field_92020_v;
    private int field_92019_w;
    private Release release;

    public GuiMainMenu() {
        this.openGLWarning2 = field_96138_a;
        this.openGLWarning1 = "";

        if (!GLContext.getCapabilities().OpenGL20)
        {
            this.openGLWarning1 = I18n.translate("title.oldgl1");
            this.openGLWarning2 = I18n.translate("title.oldgl2");
            this.openGLWarningLink = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }

        try {
            String rawJsonRelease = JSONUtils.downloadJson(new URL("https://api.github.com/repos/Utility-Client/UtilityClient/releases/latest"));
            release = (Release) JSONUtils.parseToJson(rawJsonRelease, new TypeToken<Release>(){}.getType());
        } catch (Exception e) {
            System.out.println("An error occured while getting changelog");
        }
    }

    public boolean shouldPauseGame()
    {
        return false;
    }

    protected void keyPressed(char typedChar, int keyCode) { }

    public void init()
    {
        int j = this.height / 2 - 32;

        int offset = -(this.height / 4);
        this.buttons.add(new ButtonWidget(1, this.width / 2 - 100 + offset, j, I18n.translate("menu.singleplayer")));
        this.buttons.add(new ButtonWidget(2, this.width / 2 - 100 + offset, j + 24, I18n.translate("menu.multiplayer")));
        this.buttons.add(new ButtonWidget(0, this.width / 2 - 100 + offset, j + 24 * 2, I18n.translate("menu.options")));
        this.buttons.add(new ButtonWidget(4, this.width / 2 - 100 + offset, j + 24 * 3, I18n.translate("menu.quit")));


        synchronized (this.threadLock)
        {
            int field_92023_s = textRenderer.getStringWidth(this.openGLWarning1);
            this.field_92024_r = textRenderer.getStringWidth(this.openGLWarning2);
            int k = Math.max(field_92023_s, this.field_92024_r);
            this.field_92022_t = (this.width - k) / 2;
            this.field_92021_u = buttons.get(0).y - 24;
            this.field_92020_v = this.field_92022_t + k;
            this.field_92019_w = this.field_92021_u + 24;
        }

        client.setConnectedToRealms(false);
    }

    protected void buttonClicked(ButtonWidget button)
    {
        if (button.id == 0)
        {
            client.openScreen(new SettingsScreen(this, client.options));
        }

        if (button.id == 5)
        {
            client.openScreen(new LanguageOptionsScreen(this, client.options, client.getLanguageManager()));
        }

        if (button.id == 1)
        {
            client.openScreen(new SelectWorldScreen(this));
        }

        if (button.id == 2)
        {
            client.openScreen(new MultiplayerScreen(this));
        }

        if (button.id == 4)
        {
            client.scheduleStop();
        }

        if (button.id == 11)
        {
            client.startGame("Demo_World", "Demo_World", DemoServerWorld.INFO);
        }

        if (button.id == 12)
        {
            LevelStorageAccess isaveformat = client.getCurrentSave();
            LevelProperties worldinfo = isaveformat.getLevelProperties("Demo_World");

            if (worldinfo != null)
            {
                ConfirmScreen guiyesno = SelectWorldScreen.createDeleteWarningScreen(this, worldinfo.getLevelName(), 12);
                client.openScreen(guiyesno);
            }
        }
    }

    public void confirmResult(boolean result, int id)
    {
        if (result && id == 12)
        {
            LevelStorageAccess isaveformat = client.getCurrentSave();
            isaveformat.method_254();
            isaveformat.deleteLevel("Demo_World");
            client.openScreen(this);
        }
        else if (id == 13)
        {
            if (result)
            {
                try
                {
                    Class<?> oclass = Class.forName("java.awt.Desktop");
                    Object object = oclass.getMethod("getDesktop").invoke(null);
                    oclass.getMethod("browse", URI.class).invoke(object, new URI(this.openGLWarningLink));
                }
                catch (Throwable throwable)
                {
                    logger.error("Couldn't open link", throwable);
                }
            }

            client.openScreen(this);
        }
    }

    int _longest = 0;

    public void render(int mouseX, int mouseY, float partialTicks)
    {
        this.drawUtilityClientBackground();
        GlStateManager.enableAlphaTest();
        int i = 274;
        int j = this.width / 2 - i / 2 -(this.height / 4);
        int k = 72;
        client.getTextureManager().bindTexture(minecraftTitleTextures);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        int sg = 10;
        DrawableHelper dh = new DrawableHelper();
        dh.drawTexture(j, k, 0, 0, 155 + sg, 44);
        dh.drawTexture(j + 155, k, 0, 45, 155, 44);

        GlStateManager.pushMatrix();
        GlStateManager.translatef((float)(this.width / 4), 70.0F, 0.0F);
        GlStateManager.rotatef(-20.0F, 0.0F, 0.0F, 1.0F);
        float f = 1.8F - MathHelper.abs(MathHelper.sin((float)(MinecraftClient.getTime() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
        GlStateManager.scalef(f, f, f);
        GlStateManager.popMatrix();

        try {
            String[] changes = release.body.split("-");
            if(_longest != 0) fill(_longest - 8, 0, width, height, Color.BACKGROUND.color);
            int center = (changes.length * 10) / 2, y = (width - _longest) / 2 - textRenderer.getStringWidth(release.name + " - " + release.tag_name) / 2;
            if(_longest != 0) textRenderer.drawWithShadow(release.name + " - " + release.tag_name, _longest + y, height / 2f - center - 10, -1);
            int index = 0, longest = 0;
            for (String str : changes) {
                str = str.replace("\r",""). replace("\n","");
                if(_longest != 0) textRenderer.drawWithShadow(str, _longest - 2, height / 2f - center + (index * 10), -1);
                int length = textRenderer.getStringWidth(str);
                if(length > longest) longest = length;
                index++;
            }
            _longest = width - longest - 4;
        }catch (Exception ignored) {
            textRenderer.draw("The changelog was disabled due to GitHub's rate limit.", 4, 4, -1);
        }

        if (this.openGLWarning1 != null && this.openGLWarning1.length() > 0)
        {
            fill(this.field_92022_t - 2, this.field_92021_u - 2, this.field_92020_v + 2, this.field_92019_w - 1, 1426063360);
            textRenderer.draw(this.openGLWarning1, this.field_92022_t, this.field_92021_u, -1);
            textRenderer.draw(this.openGLWarning2, (this.width - this.field_92024_r) / 2, buttons.get(0).y - 12, -1);
        }

        super.render(mouseX, mouseY, partialTicks);
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void drawUtilityClientBackground() {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        client.getTextureManager().bindTexture(new Identifier("utilityclient", "background.png"));
        // NOTE: Gui => DrawableHelper
        DrawableHelper.drawTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height, this.width, this.height);
    }
}


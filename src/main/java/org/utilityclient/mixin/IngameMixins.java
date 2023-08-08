package org.utilityclient.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.Window;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.utilityclient.UtilityClient;
import org.utilityclient.crosshair.CrosshairManager;
import org.utilityclient.overlay.Compass;
import org.utilityclient.overlay.Keystrokes;
import org.utilityclient.overlay.ModuleHandler;

@Mixin(InGameHud.class)
public class IngameMixins extends DrawableHelper {

    MinecraftClient client = MinecraftClient.getInstance();
    Compass compass = new Compass();

    @Inject(at = @At("HEAD"), method = "render(F)V", cancellable = true)
    public void render(CallbackInfo ci) {
        float tickDelta = ((ClientAccessor) client).getTricker().tickDelta;
        InGameHud _this = (InGameHud) (Object) this;
        IngameAccessor _that = (IngameAccessor)client.inGameHud;

        Window window = new Window(client);
        int i = window.getWidth();
        int j = window.getHeight();
        client.gameRenderer.setupHudMatrixMode();
        GlStateManager.enableBlend();
        if (MinecraftClient.isFancyGraphicsEnabled()) {
            _that.invokeRenderVignetteOverlay(client.player.getBrightnessAtEyes(tickDelta), window);
        } else {
            GlStateManager.blendFuncSeparate(770, 771, 1, 0);
        }

        ItemStack itemStack = client.player.inventory.getArmor(3);
        if (client.options.perspective == 0 && itemStack != null && itemStack.getItem() == Item.fromBlock(Blocks.PUMPKIN)) {
            _that.invokeRenderPumpkinBlur(window);
        }

        if (!client.player.hasStatusEffect(StatusEffect.NAUSEA)) {
            float f = client.player.lastTimeInPortal + (client.player.timeInPortal - client.player.lastTimeInPortal) * tickDelta;
            if (f > 0.0F) {
                _that.invokeRenderNausea(f, window);
            }
        }

        if (client.interactionManager.isSpectator()) {
            _this.getSpectatorHud().render(window, tickDelta);
        } else {
            _that.invokeRenderHotbar(window, tickDelta);
        }

        /* --- UTILITY CLIENT --- */
        try {
            if (UtilityClient.shouldRenderOverlay()){
                ModuleHandler.loop();
                if (client.options.viewDistance == 1) client.textRenderer.drawWithShadow(ChatFormatting.RED + "Mario mode", i - 4 - client.textRenderer.getStringWidth("Mario mode"), 16, 0);
                if (UtilityClient.getInstance().isFulbrightEnabled) client.textRenderer.drawWithShadow(ChatFormatting.GREEN + "Fulbright enabled", i - 4 - client.textRenderer.getStringWidth("Fulbright enabled"), 4, 0);
                Keystrokes.loop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        compass.render((i - 32) / 2, Math.round(j * 0.55f));
        /* --- UTILITY CLIENT --- */

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        client.getTextureManager().bindTexture(GUI_ICONS_TEXTURE);
        GlStateManager.enableBlend();

        /* --- UTILITY CLIENT --- */
        if (_that.invoke_method_9429()) {
            try {
                CrosshairManager.loop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /* --- UTILITY CLIENT --- */


        GlStateManager.blendFuncSeparate(770, 771, 1, 0);
        client.profiler.push("bossHealth");
        _that.invokeRenderBossBar();
        client.profiler.pop();
        if (client.interactionManager.hasStatusBars()) {
            _that.invokeRenderStatusBars(window);
        }

        GlStateManager.disableBlend();
        float g;
        int l;
        int k;
        if (client.player.getSleepTimer() > 0) {
            client.profiler.push("sleep");
            GlStateManager.disableDepthTest();
            GlStateManager.disableAlphaTest();
            k = client.player.getSleepTimer();
            g = (float)k / 100.0F;
            if (g > 1.0F) {
                g = 1.0F - (float)(k - 100) / 10.0F;
            }

            l = (int)(220.0F * g) << 24 | 1052704;
            fill(0, 0, i, j, l);
            GlStateManager.enableAlphaTest();
            GlStateManager.enableDepthTest();
            client.profiler.pop();
        }

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        k = i / 2 - 91;
        if (client.player.isRidingHorse()) {
            _this.renderHorseHealth(window, k);
        } else if (client.interactionManager.hasExperienceBar()) {
            _this.renderExperienceBar(window, k);
        }

        if (client.options.heldItemTooltips && !client.interactionManager.isSpectator()) {
            _this.renderHeldItemName(window);
        } else if (client.player.isSpectator()) {
            _this.getSpectatorHud().render(window);
        }

        if (client.isDemo()) {
            _this.renderDemoTime(window);
        }

        if (client.options.debugEnabled) {
            _that.getDebugHud().render(window);
        }

        int m;
        if (_that.getOverlayRemaining() > 0) {
            client.profiler.push("overlayMessage");
            g = (float)_that.getOverlayRemaining() - tickDelta;
            l = (int)(g * 255.0F / 20.0F);
            if (l > 255) {
                l = 255;
            }

            if (l > 8) {
                GlStateManager.pushMatrix();
                GlStateManager.translatef((float)(i / 2), (float)(j - 68), 0.0F);
                GlStateManager.enableBlend();
                GlStateManager.blendFuncSeparate(770, 771, 1, 0);
                m = 16777215;
                if (_that.getOverlayTinted()) {
                    m = MathHelper.hsvToRgb(g / 50.0F, 0.7F, 0.6F) & 16777215;
                }

                _this.getFontRenderer().draw(_that.getOverlayMessage(), -_this.getFontRenderer().getStringWidth(_that.getOverlayMessage()) / 2, -4, m + (l << 24 & -16777216));
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }

            client.profiler.pop();
        }

        if (_that.getTitleTotalTicks() > 0) {
            client.profiler.push("titleAndSubtitle");
            g = (float)_that.getTitleTotalTicks() - tickDelta;
            l = 255;
            if (_that.getTitleTotalTicks() > _that.getTitleFadeOutTicks() + _that.getTitleRemainTicks()) {
                float h = (float)(_that.getTitleFadeInTicks() + _that.getTitleRemainTicks() + _that.getTitleFadeOutTicks()) - g;
                l = (int)(h * 255.0F / (float)_that.getTitleFadeInTicks());
            }

            if (_that.getTitleTotalTicks() <= _that.getTitleFadeOutTicks()) {
                l = (int)(g * 255.0F / (float)_that.getTitleFadeOutTicks());
            }

            l = MathHelper.clamp(l, 0, 255);
            if (l > 8) {
                GlStateManager.pushMatrix();
                GlStateManager.translatef((float)(i / 2), (float)(j / 2), 0.0F);
                GlStateManager.enableBlend();
                GlStateManager.blendFuncSeparate(770, 771, 1, 0);
                GlStateManager.pushMatrix();
                GlStateManager.scalef(4.0F, 4.0F, 4.0F);
                m = l << 24 & -16777216;
                _this.getFontRenderer().draw(_that.getSubtitle(), (float)(-_this.getFontRenderer().getStringWidth(_that.getSubtitle()) / 2), -10.0F, 16777215 | m, true);
                GlStateManager.popMatrix();
                GlStateManager.pushMatrix();
                GlStateManager.scalef(2.0F, 2.0F, 2.0F);
                _this.getFontRenderer().draw(_that.getTitle(), (float)(-_this.getFontRenderer().getStringWidth(_that.getTitle()) / 2), 5.0F, 16777215 | m, true);
                GlStateManager.popMatrix();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }

            client.profiler.pop();
        }

        Scoreboard scoreboard = client.world.getScoreboard();
        ScoreboardObjective scoreboardObjective = null;
        Team team = scoreboard.getPlayerTeam(client.player.getTranslationKey());
        if (team != null) {
            int n = team.getFormatting().getColorIndex();
            if (n >= 0) {
                scoreboardObjective = scoreboard.getObjectiveForSlot(3 + n);
            }
        }

        ScoreboardObjective scoreboardObjective2 = scoreboardObjective != null ? scoreboardObjective : scoreboard.getObjectiveForSlot(1);
        if (scoreboardObjective2 != null) {
            _that.invokeRenderScoreboardObjective(scoreboardObjective2, window);
        }

        GlStateManager.enableBlend();
        GlStateManager.blendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableAlphaTest();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(0.0F, (float)(j - 48), 0.0F);
        client.profiler.push("chat");
        _this.getChatHud().render(_this.getTicks());
        client.profiler.pop();
        GlStateManager.popMatrix();
        scoreboardObjective2 = scoreboard.getObjectiveForSlot(0);
        if (client.options.keyPlayerList.isPressed() && (!client.isIntegratedServerRunning() || client.player.networkHandler.getPlayerList().size() > 1 || scoreboardObjective2 != null)) {
            _that.getPlayerListHud().tick(true);
            _that.getPlayerListHud().render(i, scoreboard, scoreboardObjective2);
        } else {
            _that.getPlayerListHud().tick(false);
        }

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        GlStateManager.enableAlphaTest();
        ci.cancel();
    }
}

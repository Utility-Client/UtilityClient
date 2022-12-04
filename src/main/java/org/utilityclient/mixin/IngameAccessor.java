package org.utilityclient.mixin;

import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.util.Window;
import net.minecraft.scoreboard.ScoreboardObjective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(InGameHud.class)
public interface IngameAccessor {
    @Invoker("renderVignetteOverlay")
    void invokeRenderVignetteOverlay(float tickDelta, Window window);

    @Invoker("renderPumpkinBlur")
    void invokeRenderPumpkinBlur(Window window);

    @Invoker("renderNausea")
    void invokeRenderNausea(float f, Window window);

    @Invoker("renderHotbar")
    void invokeRenderHotbar(Window window, float tickDelta);

    @Invoker("method_9429")
    boolean invoke_method_9429();

    @Invoker("renderBossBar")
    void invokeRenderBossBar();

    @Invoker("renderStatusBars")
    void invokeRenderStatusBars(Window window);

    @Invoker("renderScoreboardObjective")
    void invokeRenderScoreboardObjective(ScoreboardObjective so, Window window);

    @Accessor()
    DebugHud getDebugHud();

    @Accessor()
    int getOverlayRemaining();

    @Accessor()
    boolean getOverlayTinted();

    @Accessor()
    String getOverlayMessage();

    @Accessor()
    int getTitleTotalTicks();

    @Accessor
    int getTitleFadeOutTicks();

    @Accessor
    int getTitleFadeInTicks();

    @Accessor
    int getTitleRemainTicks();

    @Accessor
    String getTitle();

    @Accessor
    String getSubtitle();

    @Accessor
    PlayerListHud getPlayerListHud();

}

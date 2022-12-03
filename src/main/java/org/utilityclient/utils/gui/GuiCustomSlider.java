package org.utilityclient.utils.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.util.math.MathHelper;

import java.text.DecimalFormat;

public class GuiCustomSlider extends ButtonWidget
{
    private float sliderValue;
    public boolean dragging;
    private final ValueUpdateEvent event;

    public GuiCustomSlider(int buttonId, int x, int y, ValueUpdateEvent event, float default_value)
    {
        super(buttonId, x, y, 200, 20, "");
        this.event = event;
        this.sliderValue = default_value;
        this.message = "Zoom factor: " + new DecimalFormat("0.00").format(sliderValue);
        if(sliderValue == 1f) message = "Zoom factor: Disabled";
        if(sliderValue > 1f) message = "Zoom factor: Quake Pro+";
        if(sliderValue >= 2f) message = "Zoom factor: Quake Pro++";
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    public int getHoverState(boolean mouseOver)
    {
        return 0;
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(MinecraftClient mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            if (this.dragging)
            {
                getSliderValue(mouseX);
            }

            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexture(x + (int)(sliderValue * (float)(this.width - 8)), y, 0, 66, 4, 20);
            this.drawTexture(x + (int)(sliderValue * (float)(this.width - 8)) + 4, y, 196, 66, 4, 20);
        }
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(MinecraftClient mc, int mouseX, int mouseY)
    {
        if (super.isMouseOver(mc, mouseX, mouseY))
        {
            getSliderValue(mouseX);
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    private void getSliderValue(int mouseX) {
        this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
        this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);
        event.onUpdate(sliderValue);
        this.message = "Zoom factor: " + new DecimalFormat("0.00").format(sliderValue);
        if(sliderValue == 1f) message = "Zoom factor: Disabled";
        if(sliderValue > 1f) message = "Zoom factor: Quake Pro+";
        if(sliderValue >= 2f) message = "Zoom factor: Quake Pro++";
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY)
    {
        this.dragging = false;
    }
}

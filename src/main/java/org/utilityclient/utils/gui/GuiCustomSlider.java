package org.utilityclient.utils.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

import java.text.DecimalFormat;

public class GuiCustomSlider extends GuiButton
{
    private float sliderValue;
    public boolean dragging;
    private final ValueUpdateEvent event;

    public GuiCustomSlider(int buttonId, int x, int y, ValueUpdateEvent event, float default_value)
    {
        super(buttonId, x, y, 200, 20, "");
        this.event = event;
        this.sliderValue = default_value;
        this.displayString = "Zoom factor: " + new DecimalFormat("0.00").format(sliderValue);
        if(sliderValue == 1f) displayString = "Zoom factor: Disabled";
        if(sliderValue > 1f) displayString = "Zoom factor: Quake Pro+";
        if(sliderValue >= 2f) displayString = "Zoom factor: Quake Pro++";
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
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            if (this.dragging)
            {
                getSliderValue(mouseX);
            }

            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (super.mousePressed(mc, mouseX, mouseY))
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
        this.sliderValue = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
        this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
        event.onUpdate(sliderValue);
        this.displayString = "Zoom factor: " + new DecimalFormat("0.00").format(sliderValue);
        if(sliderValue == 1f) displayString = "Zoom factor: Disabled";
        if(sliderValue > 1f) displayString = "Zoom factor: Quake Pro+";
        if(sliderValue >= 2f) displayString = "Zoom factor: Quake Pro++";
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY)
    {
        this.dragging = false;
    }
}

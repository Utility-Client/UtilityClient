package org.utilityclient.gui;

import org.utilityclient.gui.components.GuiComponent;
import org.utilityclient.gui.components.GuiScreen;

/**
 * Helper methods to position GuiComponents.
 * @author Sam302
 * @since 3.0
 * @apiNote Documentation is a bit vague. Should this be an issue, please create an Issue on GitHub. I'll (@sam302) add proper docs then.
 */
public class GuiHelper {
    public static int below(GuiComponent other, int margin) {
        return other.screenY + other.height + margin;
    }

    /**
     * @apiNote Only works in an absolute context/without a parent.
     */
    public static int above(GuiComponent self, GuiComponent other, int margin) {
        return other.screenY - self.height - margin;
    }
    public static int after(GuiComponent other, int margin) {
        return other.screenX + other.width + margin;
    }

    /**
     * @apiNote Only works in an absolute context/without a parent.
     */
    public static int before(GuiComponent self, GuiComponent other, int margin) {
        return other.screenX - self.width - margin;
    }

    /**
     * Horizontal centered alignment with the parent component.
     */
    public static int centerX(GuiComponent self) {
        return self.parent.width / 2 - self.width / 2;
    }

    /**
     * Horizontal centered alignment with a GuiScreen.
     */
    public static int centerX(GuiComponent self, GuiScreen screen) {
        return screen.width / 2 - self.width / 2;
    }

    /**
     * Vertical centered alignment with the parent component.
     */
    public static int centerY(GuiComponent self) {
        return self.parent.height / 2 - self.height / 2;
    }

    /**
     * Vertical centered alignment with a GuiScreen.
     */
    public static int centerY(GuiComponent self, GuiScreen screen) {
        return screen.height / 2 - self.height / 2;
    }
}

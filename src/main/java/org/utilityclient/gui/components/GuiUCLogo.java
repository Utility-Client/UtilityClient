package org.utilityclient.gui.components;

import org.utilityclient.api.abstraction.StandaloneCompatible;
import org.utilityclient.utils.RenderHelper;

@StandaloneCompatible
public class GuiUCLogo extends GuiComponent {
    protected Variant variant;
    protected boolean center;

    public GuiUCLogo(int x, int y, float scale, Variant variant, boolean center) {
        super(x, y, Math.round(variant.width * scale), Math.round(variant.height * scale));
        this.variant = variant;
        this.center = center;
    }

    public GuiUCLogo(int x, int y, float scale, Variant variant, boolean center, GuiComponent parent) {
        super(x, y, Math.round(variant.width * scale), Math.round(variant.height * scale), parent);
        this.variant = variant;
        this.center = center;
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        int _x = screenX;
        if (center) _x -= width / 2;

        RenderHelper.texture(_x, screenY, width, height, variant.getIdentifier(), 0, 0, variant.width, variant.height, variant.width, variant.height, true);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {

    }

    public enum Variant {
        UC("uc.png", 133, 73),
        UC3("uc3.png", 296, 175),
        UtilityClient("utilityclient.png", 0, 0),
        UtilityClient3("utilityclient3.png", 0, 0);

        private final String id;
        public final int width, height;

        Variant(String iconPath, int width, int height) {
            id = "textures/utilityclient/icons/" + iconPath;
            this.width = width;
            this.height = height;
        }

        public String getIdentifier() {
            return id;
        }
    }
}

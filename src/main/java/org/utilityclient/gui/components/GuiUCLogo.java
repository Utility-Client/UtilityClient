package org.utilityclient.gui.components;

import net.minecraft.util.Identifier;
import org.utilityclient.utils.RenderHelper;

public class GuiUCLogo extends GuiComponent {
    protected Variant variant;

    public GuiUCLogo(int x, int y, float scale, Variant variant) {
        super(x, y, Math.round(variant.width * scale), Math.round(variant.height * scale));
        this.variant = variant;
    }

    public GuiUCLogo(int x, int y, float scale, Variant variant, GuiComponent parent) {
        super(x, y, Math.round(variant.width * scale), Math.round(variant.height * scale), parent);
        this.variant = variant;
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        RenderHelper.texture(screenX, screenY, width, height, variant.getIdentifier(), 0, 0, variant.width, variant.height, variant.width, variant.height, true);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {

    }

    public enum Variant {
        UC("uc.png", 133, 73),
        UC3("uc3.png", 296, 175),
        UtilityClient("utilityclient.png", 0, 0),
        UtilityClient3("utilityclient3.png", 0, 0);

        private Identifier id;
        public final int width, height;

        Variant(String iconPath, int width, int height) {
            id = new Identifier("textures/utilityclient/icons/" + iconPath);
            this.width = width;
            this.height = height;
        }

        public Identifier getIdentifier() {
            return id;
        }
    }
}

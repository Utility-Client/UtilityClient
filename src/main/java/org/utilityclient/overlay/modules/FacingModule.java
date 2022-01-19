package org.utilityclient.overlay.modules;

import net.minecraft.client.Minecraft;
import org.utilityclient.overlay.IModule;

public class FacingModule implements IModule {
    @Override
    public String getName() {
        return "Facing";
    }

    @Override
    public String getValue() {
        String str = mc().getRenderViewEntity().getHorizontalFacing().getName();
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @Override
    public String getAuthor() {
        return "marioboss56";
    }
}

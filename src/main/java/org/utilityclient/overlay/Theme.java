package org.utilityclient.overlay;

import net.minecraft.util.EnumChatFormatting;

public enum Theme {
    RED(0, EnumChatFormatting.DARK_RED, EnumChatFormatting.RED),
    YELLOW(1, EnumChatFormatting.GOLD, EnumChatFormatting.YELLOW),
    GREEN(2, EnumChatFormatting.DARK_GREEN, EnumChatFormatting.GREEN),
    BLUE(3, EnumChatFormatting.DARK_BLUE, EnumChatFormatting.BLUE),
    WHITE(4, EnumChatFormatting.WHITE, EnumChatFormatting.WHITE),
    BLACK(5, EnumChatFormatting.BLACK, EnumChatFormatting.DARK_GRAY),
    CONTRAST(6, EnumChatFormatting.BLACK, EnumChatFormatting.WHITE),
    PURPLE(7, EnumChatFormatting.DARK_PURPLE, EnumChatFormatting.LIGHT_PURPLE),
    GRAY(8, EnumChatFormatting.DARK_GRAY, EnumChatFormatting.GRAY),
    AQUA(9, EnumChatFormatting.DARK_AQUA, EnumChatFormatting.DARK_AQUA),
    STANDARD_GALACTIC(10, EnumChatFormatting.DARK_RED, EnumChatFormatting.RED);

    private final int id;
    private final EnumChatFormatting prefix, suffix;

    Theme(int id, EnumChatFormatting prefix, EnumChatFormatting suffix) {
        this.id = id;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public int getId() {
        return id;
    }
    public EnumChatFormatting getPrefix() {
        return prefix;
    }
    public EnumChatFormatting getSuffix() {
        return suffix;
    }

    public static Theme getThemeById(int id) {
        for (Theme t : Theme.values()) if(t.getId() == id) return t;
        return RED;
    }
}

package de.gamingcraft.overlay;

public enum Theme {
    RED(0, '4', 'c'),
    YELLOW(1, '6', 'e'),
    GREEN(2, '2', 'a'),
    BLUE(3, '1', '9'),
    WHITE(4, 'f', 'f'),
    BLACK(5, '0', '8'),
    CONTRAST(6, '0', 'f');

    private final int id;
    private final char prefix;
    private final char suffix;

    Theme(int id, char prefix, char suffix) {
        this.id = id;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public int getId() {
        return id;
    }

    public String getPrefix() {
        return "§"+prefix;
    }

    public String getSuffix() {
        return "§"+suffix;
    }

    public static Theme getThemeById(int id) {
        for (Theme t : Theme.values()) {
            if(t.getId() == id) {
                return t;
            }
        }

        return RED;
    }
}

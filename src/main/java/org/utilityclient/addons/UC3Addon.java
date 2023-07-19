package org.utilityclient.addons;

public abstract class UC3Addon {
    public final Metadata Metadata;

    public UC3Addon(Metadata meta) {
        this.Metadata = meta;
    }

    public static void listen(Event event) {
        AddonManager.registerEvent(event);
    }

    public static class Metadata {
        public final String Name;
        public final String Description;
        public final String[] Authors;
        public final String Version;

        public Metadata(String name, String description, String[] authors, String version) {
            this.Name = name;
            this.Description = description;
            this.Authors = authors;
            this.Version = version;
        }
    }
}

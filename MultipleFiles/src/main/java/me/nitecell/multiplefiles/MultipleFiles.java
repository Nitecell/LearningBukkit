package me.nitecell.multiplefiles;

import org.bukkit.plugin.java.JavaPlugin;

public final class MultipleFiles extends JavaPlugin {

    private static ConfigFile settings;
    private static ConfigFile maps;

    @Override
    public void onEnable() {
        settings = new ConfigFile(this, "settings");
        maps = new ConfigFile(this, "maps");
    }

    public static ConfigFile getSettings() { return settings; }
    public static ConfigFile getMaps() { return maps; }

}

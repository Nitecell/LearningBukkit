package com.nitecell.punishments.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private ConfigManager() { }
    static ConfigManager instance = new ConfigManager();
    public static ConfigManager getInstance() { return instance; }

    Plugin p;

    FileConfiguration configurationFile;
    File config;

    public void setup(Plugin p) {
        config = new File(p.getDataFolder(), "config.yml");
        configurationFile = p.getConfig();

    }

    public FileConfiguration getConfig() {
        return configurationFile;
    }

    public void saveConfig() {
        try {
            configurationFile.save(config);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
        }
    }

    public void reloadConfig() {
        configurationFile = YamlConfiguration.loadConfiguration(config);
    }

}

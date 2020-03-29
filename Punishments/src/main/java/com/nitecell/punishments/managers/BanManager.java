package com.nitecell.punishments.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class BanManager {

    private BanManager() { }
    static BanManager instance = new BanManager();
    public static BanManager getInstance() { return instance; }

    FileConfiguration banlist;
    File bans;

    public void setup(Plugin p) {
        if (!(p.getDataFolder().exists())) {
            p.getDataFolder().mkdir();
            Bukkit.getServer().getLogger().fine("Data Folder did not exist, creating one!");
        }

        bans = new File(p.getDataFolder(), "bans.yml");
        if (!bans.exists()) {
            try {
                bans.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create bans.yml!");
            }
        }
        banlist = YamlConfiguration.loadConfiguration(bans);
    }

    public FileConfiguration getBanlist() {
        return banlist;
    }
    public void saveBanlist() {
        try {
            banlist.save(bans);
        } catch (IOException e) {
            System.out.println(ChatColor.RED + "Could not save bans.yml!");
        }
    }
    public void reloadBanlist() {
        banlist = YamlConfiguration.loadConfiguration(bans);
    }

}

package com.nitecell.announcements;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Announcements extends JavaPlugin {

    FileConfiguration config;
    File cfile;

    @Override
    public void onEnable() {
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
        cfile = new File(getDataFolder(), "config.yml");

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().broadcastMessage(ChatColor.GREEN + config.getString("announcement"));

            }
        }, 20, 1000);

    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("announcements-reload")) {
            config = YamlConfiguration.loadConfiguration(cfile);
            sender.sendMessage(ChatColor.GREEN + "Announcements Reloaded.");
            return true;
        }
        return true;
    }
}

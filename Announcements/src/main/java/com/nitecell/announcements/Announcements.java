package com.nitecell.announcements;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Announcements extends JavaPlugin {

    SettingsManager settings = SettingsManager.getInstance();

    @Override
    public void onEnable() {
        SettingsManager.getInstance().setup(this);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().broadcastMessage(ChatColor.GREEN + settings.getConfig().getString("announcement"));

            }
        }, 20, 1000);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("announcements-reload")) {
            settings.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Announcements Reloaded.");
            return true;
        }
        return true;
    }
}

package com.nitecell.motd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MOTD extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        System.out.println(ChatColor.GREEN + "[MOTD] has been enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[MOTD] has been disabled.");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GREEN + getConfig().getString("message"));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("motd")) {
            sender.sendMessage(getConfig().getString("message"));
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("setmotd")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments.");
                return true;
            }
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                str.append(args[i] + " ");
            }
            String motd = str.toString();
            getConfig().set("message", motd);
            saveConfig();
            sender.sendMessage(ChatColor.GREEN + "New MOTD saved to the configuration file.");
            return true;
        }
        return false;
    }
}
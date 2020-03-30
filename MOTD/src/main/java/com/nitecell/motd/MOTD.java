package com.nitecell.motd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
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
    public void onPing(ServerListPingEvent event) {
        String motd = (String) getConfig().getString("motd.server");
        motd = motd.replaceAll("&", "\u00A7");
        event.setMotd(motd);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GREEN + getConfig().getString("motd.ingame"));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("motd")) {
            if(!sender.hasPermission("motd.ingame.check")) {
                String motd = (String) getConfig().getString("motd.ingame");
                motd = motd.replaceAll("&", "\u00A7");
                sender.sendMessage(motd);
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to do this.");
                return true;
            }

        }
        if (cmd.getName().equalsIgnoreCase("setmotd")) {
            if (!sender.hasPermission("motd.ingame.set")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments.");
                return true;
            }
            StringBuilder str = new StringBuilder();
            for (String arg : args) {
                str.append(arg).append(" ");
            }
            String motd = str.toString();
            getConfig().set("motd.server", motd);
            saveConfig();
            sender.sendMessage(ChatColor.GREEN + "New MOTD saved to the configuration file.");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("setservermotd")) {
            if (!sender.hasPermission("motd.server.set")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments.");
                return true;
            }
            StringBuilder str = new StringBuilder();
            for (String arg : args) {
                str.append(arg).append(" ");
            }
            String motd = str.toString();
            getConfig().set("motd.server", motd);
            saveConfig();
            sender.sendMessage(ChatColor.GREEN + "New Server MOTD saved to the configuration file.");
            return true;
        }
        return false;
    }
}
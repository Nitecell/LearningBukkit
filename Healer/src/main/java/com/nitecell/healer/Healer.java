package com.nitecell.healer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Healer extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "[Healer] has been enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[Healer] has been disabled.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "The console cannot use healer commands!");
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (args.length == 0) {
                player.setHealth(20);
                player.sendMessage(ChatColor.GREEN + "You have been healed!");
                return true;
            }
            if (args.length == 1) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "That user does not exist.");
                    return true;
                }
                target.setHealth(20);
                target.sendMessage(ChatColor.GREEN + "You have been healed!");
                player.sendMessage(ChatColor.GREEN + target.getName() + "has been healed!");
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "There are too many arguments.");
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("feed")) {
            if (args.length == 0) {
                player.setFoodLevel(20);
                player.sendMessage(ChatColor.GREEN + "You have been fed!");
                return true;
            }
            if (args.length == 1) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "That user does not exist.");
                    return true;
                }
                target.setFoodLevel(20);
                target.sendMessage(ChatColor.GREEN + "You have been fed!");
                player.sendMessage(ChatColor.GREEN + target.getName() + "has been fed!");
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "There are too many arguments.");
                return true;
            }
        }

        return false;
    }
}

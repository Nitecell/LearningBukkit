package com.nitecell.worldstar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Worldstar extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "[Worldstar] has been enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[Worldstar] has been disabled.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("fakeop")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments");
                return true;
            }
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Could not find " + args[0] + ".");
                return true;
            }
            if (args.length > 1) {
                sender.sendMessage(ChatColor.RED + "Too many arguments.");
                return true;
            }
            target.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "[Server: Made " + target.getName() + " a server operator]");
            sender.sendMessage(ChatColor.GREEN + "You have fake op'ed " + target.getName() + ".");
            return true;

        }
        if (cmd.getName().equalsIgnoreCase("fakejoin")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments.");
                return true;
            }
            if (args.length > 1) {
                sender.sendMessage(ChatColor.RED + "Too many arguments.");
                return true;
            }
            if (!(args[0].length() >= 3 && args[0].length() <= 16)) {
                sender.sendMessage(ChatColor.RED + "Invalid username!");
                //does not support invalid characters (e.g. $)
                return true;
            }
            Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + args[0] + " joined the game");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("fakeleave")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments.");
                return true;
            }
            if (args.length > 1) {
                sender.sendMessage(ChatColor.RED + "Too many arguments.");
                return true;
            }
            if (!(args[0].length() >= 3 && args[0].length() <= 16)) {
                sender.sendMessage(ChatColor.RED + "Invalid username!");
                //does not support invalid characters (e.g. $)
                return true;
            }
            Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + args[0] + " left the game");
            return true;
        }

        return false;
    }
}
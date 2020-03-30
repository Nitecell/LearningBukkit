package me.nitecell.nitepunishments.commands;

import me.nitecell.nitepunishments.managers.PunishmentsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanCommand implements CommandExecutor {

    PunishmentsManager plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("nitepunishments.ban")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission for this command.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments.");
            return true;
        }
        if (Bukkit.getServer().getPlayer(args[0]) == null) {

        } else if (getOfflinePlayer(args[0]) != null) {

        } else {
            sender.sendMessage(ChatColor.RED + "Specified user could not be found.");
            return true;
        }



        return false;
    }

    public void addBan() {

    }

    public OfflinePlayer getOfflinePlayer(String name) {
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    public String getReason(String[] args) {
        if (args.length == 1) {
            return "The boot has spoken!";
        }
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            str.append(args[i]).append(" ");
        } return str.toString();
    }

}

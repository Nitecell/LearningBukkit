package com.nitecell.punishments.commands;

import com.nitecell.punishments.managers.BanManager;
import com.nitecell.punishments.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        BanManager banlist = BanManager.getInstance();
        ConfigManager config = ConfigManager.getInstance();

        if (command.getName().equalsIgnoreCase("unban")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "No username specified.");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            String targetuuid = Bukkit.getPlayer(args[0]).getUniqueId().toString();

            if (banlist.getBanlist().get(target.getUniqueId() + ".status") == "banned") {
                banlist.getBanlist().set(target.getUniqueId() + ".status", "inactive");
                sender.sendMessage(ChatColor.GREEN + target.getName() + " has been unbanned.");
                banlist.saveBanlist();
            }
            return true;
        }

        return true;
    }
}

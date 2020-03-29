package com.nitecell.punishments.commands;

import com.nitecell.punishments.managers.BanManager;
import com.nitecell.punishments.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {

    BanManager banlist = BanManager.getInstance();
    ConfigManager config = ConfigManager.getInstance();

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        if (command.getName().equalsIgnoreCase("ban")) {

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments.");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            OfflinePlayer offlinetarget = getOfflinePlayer(args[0]);
            String offlineuuid = offlinetarget.getUniqueId().toString();

            if (!(target == null)) {
                addBan(sender, args, target.getUniqueId().toString());
                target.kickPlayer(config.getConfig().getString("ban-kick-message"));
                Bukkit.getServer().broadcastMessage(ChatColor.GREEN + target.getName() + "has been banned by " + sender.getName() + ".");
                return true;
            } else if (offlinetarget.getPlayer() == null) {
                addBan(sender, args, offlineuuid);
                Bukkit.getServer().broadcastMessage(ChatColor.GREEN + args[0] + "has been banned by" + sender.getName() + ".");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "Specified user does not exist.");
            return true;
        }
        return true;
    }

    private void addBan(CommandSender sender, String[] args, String targetuuid) {
        int banid = 0;
        increaseBanID(banid);
        banid = banlist.getBanlist().getInt("total-bans");
        banlist.getBanlist().set(targetuuid + ".status", "banned");
        if (args.length == 1) {
            banlist.getBanlist().set(targetuuid + ".bans." + banid + ".reason", config.getConfig().get("default-ban-reason"));
        } else {
            banlist.getBanlist().set(targetuuid + ".bans." + banid + ".reason", addReason(args));
        }
        banlist.getBanlist().set(targetuuid + ".bans." + banid + ".punisher", sender.getName());
        banlist.saveBanlist();
    }

    public String addReason(String[] args) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < args.length - 1; i++) {
            str.append(args[++i]).append(" ");
        }
        return str.toString();
    }

    public void increaseBanID(int banid) {
        banid = banlist.getBanlist().getInt("total-bans");
        banlist.getBanlist().set("total-bans", banid + 1);
        banlist.saveBanlist();
    }

    public OfflinePlayer getOfflinePlayer(String name) {
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

}
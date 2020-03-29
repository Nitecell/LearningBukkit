package me.nitecell.nitepunishments.commands;

import me.nitecell.nitepunishments.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

    //private NitePunishments plugin = NitePunishments.getPlugin(NitePunishments.class);
    ConfigManager punishments = ConfigManager.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int kickid = 0;

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments.");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Specified user could not be found.");
            return true;
        } else {
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Entering else statement in command ");
            String targetuuid = target.getUniqueId().toString();
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set targetuuid string");
            kickid = 1; //newKickID();
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] newKickID: " + kickid);
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set kickid=newkickid");
            //Date date = new Date();
            //Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set date = date");

            Bukkit.getServer().broadcastMessage(targetuuid);
            Bukkit.getServer().broadcastMessage(target.getName());

            punishments.getPunishments().set("punishments." + targetuuid + ".username", target.getName());
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set Punishments Username");
            punishments.getPunishments().set("punishments." + targetuuid + ".kicks." + kickid + ".reason", getReason(args));
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set reason");
            //punishments.getPunishments().set("punishments." + targetuuid + ".kicks." + kickid + ".time", date);
            //Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set date");
            punishments.getPunishments().set("punishments." + targetuuid + ".kicks." + kickid + ".location.x", target.getLocation().getX());
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set X");
            punishments.getPunishments().set("punishments." + targetuuid + ".kicks." + kickid + ".location.y", target.getLocation().getY());
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set Y");
            punishments.getPunishments().set("punishments." + targetuuid + ".kicks." + kickid + ".location.z", target.getLocation().getZ());
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set Z");
            punishments.getPunishments().set("punishments." + targetuuid + ".kicks." + kickid + ".punisher", sender.getName());
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set Punisher");
            punishments.savePunishments();
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Saved Punishments");
            target.kickPlayer("You have been kicked!");
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Kicked Player");
            return true;
        }
    }

    /**public int newKickID() {
        Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Entered newKickID");
        int newid = punishments.getPunishments().getInt("total-kicks") + 1;
        Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] Set newid = punishments total-kicks");
        punishments.getConfig().set("total-kicks", newid);
        Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Debug] set new newid");
        return newid;
    }**/

    public String getReason(String[] args) {
        if (args.length == 1) {
            return "You have been kicked!";
        }
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            str.append(args[i]).append(" ");
        } return str.toString();
    }
}

package com.nitecell.teleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Teleport extends JavaPlugin {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("teleport")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command cannot be executed by console.");
                return true;
            }
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Not enough arguments.");
                return true;
            }
            if (args.length > 1) {
                p.sendMessage(ChatColor.RED + "Too many arguments.");
                return true;
            }

            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                p.sendMessage(ChatColor.RED + "Could not find the specified user.");
            }
            p.teleport(target);
            Bukkit.getServer().getPluginManager().callEvent(new PlayerTeleportEvent(p, target));
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("setspawn")) {

            if (args.length == 0) {

                getConfig().set("spawn.world", p.getLocation().getWorld().getName());
                getConfig().set("spawn.x", p.getLocation().getX());
                getConfig().set("spawn.y", p.getLocation().getY());
                getConfig().set("spawn.z", p.getLocation().getZ());
                getConfig().set("spawn.pitch", p.getLocation().getPitch());
                getConfig().set("spawn.yaw", p.getLocation().getYaw());
                saveConfig();
                p.sendMessage(ChatColor.GREEN + "Spawn has been set at: " + ChatColor.GRAY + getConfig().get("spawn.x") + ", " + getConfig().get("spawn.y") + ", " + getConfig().get("spawn.z"));
                return true;

            }
        }
        return false;
    }

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new EventTester(), this);
        System.out.println(ChatColor.GREEN + "[Teleport] has been enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[Teleport] has been disabled.");
    }
}
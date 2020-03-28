package com.nitecell.freeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Freeze extends JavaPlugin implements Listener {

    ArrayList<String> frozen = new ArrayList<String>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (frozen.contains(p.getName())) {
            event.setTo(event.getFrom());
        }
    }

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("freeze")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Please specify a player.");
                return true;
            }
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Specified user could not be found.");
                return true;
            }
            if (frozen.contains(target.getName())) {
                frozen.remove(target.getName());
                sender.sendMessage(ChatColor.GREEN + target.getName() + " has been unfrozen.");
                return true;
            }
            frozen.add(target.getName());
            sender.sendMessage(ChatColor.GREEN + target.getName() + " has been frozen.");
        }

        return true;
    }

}

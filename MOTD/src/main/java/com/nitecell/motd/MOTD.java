package com.nitecell.motd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MOTD extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "[MOTD] has been enabled.");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[MOTD] has been disabled.");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GREEN + "Welcome to the Test Server!");
    }
}
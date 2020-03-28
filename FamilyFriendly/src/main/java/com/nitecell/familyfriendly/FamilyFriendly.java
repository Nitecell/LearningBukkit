package com.nitecell.familyfriendly;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class FamilyFriendly extends JavaPlugin implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        for (String word : event.getMessage().split(" ")) {
            if ( getConfig().getStringList("blacklisted-words").contains(word)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "Do not swear!");
            }
        }
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }
}

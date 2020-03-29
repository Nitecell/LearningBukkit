package com.nitecell.teleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EventTester implements Listener {

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Bukkit.getServer().broadcastMessage(ChatColor.GREEN + event.getPlayer().getName() + " teleported to " + event.t.getName());
    }

}

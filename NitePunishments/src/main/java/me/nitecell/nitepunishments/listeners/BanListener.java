package me.nitecell.nitepunishments.listeners;

import me.nitecell.nitepunishments.NitePunishments;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class BanListener implements Listener {

    private NitePunishments plugin = NitePunishments.getPlugin(NitePunishments.class);

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {

    }

}

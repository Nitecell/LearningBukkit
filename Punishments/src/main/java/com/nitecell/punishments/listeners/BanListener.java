/**package com.nitecell.punishments.listeners;

import com.nitecell.punishments.managers.BanManager;
import com.nitecell.punishments.managers.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BanListener implements Listener {

    BanManager banlist = BanManager.getInstance();
    ConfigManager config = ConfigManager.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();
        String uuid = p.getUniqueId().toString();

        if (banlist.getBanlist().contains(uuid) && banlist.getBanlist().get(uuid + ".status") == "banned") {
            p.kickPlayer((String) config.getConfig().get("ban-kick-message"));
        }
    }

}**/

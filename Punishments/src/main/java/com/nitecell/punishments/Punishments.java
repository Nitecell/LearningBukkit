package com.nitecell.punishments;

import com.nitecell.punishments.commands.BanCommand;
import com.nitecell.punishments.commands.UnbanCommand;
import com.nitecell.punishments.managers.BanManager;
import com.nitecell.punishments.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Punishments extends JavaPlugin implements Listener {

    BanManager banlist = BanManager.getInstance();
    ConfigManager config = ConfigManager.getInstance();

    @Override
    public void onEnable() {
        config.setup(this);
        banlist.setup(this);

        banlist.reloadBanlist();
        this.getCommand("ban").setExecutor(new BanCommand());
        this.getCommand("unban").setExecutor(new UnbanCommand());
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        System.out.println(ChatColor.GREEN + "[Punishments] has been enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[Punishments] has been disabled.");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();
        String uuid = p.getUniqueId().toString();

        if (banlist.getBanlist().contains(uuid) && banlist.getBanlist().get(uuid + ".status") == "banned") {
            p.kickPlayer((String) config.getConfig().get("ban-kick-message"));
        }
    }


}

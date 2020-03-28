package com.nitecell.healer;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class HealerListener implements Listener {

    @EventHandler
    //Sign Change Event not Guarded with Permission
    public void onSignChange(SignChangeEvent event) {
        if (event.getLine(0).equalsIgnoreCase("[Heal]")) {
            event.setLine(0, "§4[Heal]");
            event.setLine(1, "§fClick to Heal!");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) event.getClickedBlock().getState();
            if (s.getLine(0).equalsIgnoreCase("§4[Heal]")) {
                event.getPlayer().setHealth(20);
                event.getPlayer().sendMessage(ChatColor.GREEN + "You have been healed!");
            }
        }
    }
}

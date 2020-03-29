package com.nitecell.teleport;


import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTeleportEvent extends Event {

    Player p;
    Player t;

    public PlayerTeleportEvent(Player p, Player t) {
        this.p = p;
        this.t = t;
    }

    public Player getPlayer() {
        return p;
    }

    public Player getTeleportTarget() {
        return t;
    }

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}

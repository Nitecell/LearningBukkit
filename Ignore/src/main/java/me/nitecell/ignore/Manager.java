package me.nitecell.ignore;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

    private Manager() {}
    static Manager instance = new Manager();
    public static Manager getInstance() { return instance; }

    HashMap<Player, ArrayList<Player>> ignore = new HashMap<Player, ArrayList<Player>>();

    public HashMap<Player, ArrayList<Player>> getIgnore() {
        return ignore;
    }

}

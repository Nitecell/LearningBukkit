package me.nitecell.econite;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Econite extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "[Econite] has been enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[Econite] has been disabled.");
    }
}

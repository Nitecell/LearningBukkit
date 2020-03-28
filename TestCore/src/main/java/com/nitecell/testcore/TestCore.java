package com.nitecell.testcore;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestCore extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println(ChatColor.GREEN + "[TestCore] has been enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[TestCore] has been disabled.");
    }
}

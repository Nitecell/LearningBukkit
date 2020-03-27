package com.nitecell.basicCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    String consoleprefix = ChatColor.GREEN + "[BasicCommand]: ";

    public void onEnable() {
        System.out.print(consoleprefix + "has been enabled!");
    }

    public void onDisable() {
        System.out.print(consoleprefix + "has been disabled!");
    }

    public boolean onCommand(Command sender, Command cmd, String commandLabel, String[] args) {

        Player player = (Player) sender;

        if (commandLabel.equalsIgnoreCase("helloworld")) {
            player.sendMessage(ChatColor.GREEN + "Hello, World!");
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Command Executed by: " + ChatColor.WHITE + player.getName());
        }

        return false;
    }


}

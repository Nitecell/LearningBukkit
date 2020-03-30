package me.nitecell.ignore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ignore extends JavaPlugin {

    public boolean onCommand(CommandSender sender, Command command,String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command cannot be executed by console.");
            return true;
        }

        Player p = (Player) sender;

        if (command.getName().equalsIgnoreCase("ignore")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Please specify a player");
            }
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Could not find the specified user.");
                return true;
            }
            return true;
        }
        return true;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerChat(), this);
    }

    @Override
    public void onDisable() {
    }
}

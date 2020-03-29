package me.nitecell.nitepunishments;

import me.nitecell.nitepunishments.commands.KickCommand;
import me.nitecell.nitepunishments.managers.PunishmentsManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class NitePunishments extends JavaPlugin {

    PunishmentsManager punishments = PunishmentsManager.getInstance();

    @Override
    public void onEnable() {

        punishments.setup(this);
        registerCommands();
        //registerListeners();

        System.out.println(ChatColor.GREEN + "[NitePunishments] has been enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[NitePunishments] has been disabled.");
    }

    private void registerCommands() {
        getCommand("kick").setExecutor(new KickCommand());
    }

    //private void registerListeners() {
        //Bukkit.getServer().getPluginManager().registerEvents(new punishmentsener(), this);
    //}

    public void loadConfig () {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}

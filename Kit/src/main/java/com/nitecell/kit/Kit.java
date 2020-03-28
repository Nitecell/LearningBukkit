package com.nitecell.kit;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Kit extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    ArrayList<Player> cooldown = new ArrayList<Player>();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command cannot be executed by console.");
            return true;
        }

        final Player p = (Player) sender;
        PlayerInventory pi = p.getInventory();

        if (cmd.getName().equalsIgnoreCase("kit")) {
            if (cooldown.contains(p)) {
                p.sendMessage(ChatColor.RED + "You cannot get another kit at the moment. Cooldown!");
                return true;
            }
            EconomyResponse r = econ.withdrawPlayer(p.getName(), 10);
            if (r.transactionSuccess()) {
                pi.addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
                pi.addItem(new ItemStack(Material.DIAMOND_PICKAXE, 1));
                pi.addItem(new ItemStack(Material.COOKED_BEEF, 16));
                p.sendMessage(ChatColor.GREEN + "Kit has been given!");
                cooldown.add(p);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    @Override
                    public void run() {
                        cooldown.remove(p);

                    }
                }, 100);
                return true;
            } else {
                p.sendMessage(ChatColor.RED + "Insufficient Funds.");
                return true;
            }

        }

        if (cmd.getName().equalsIgnoreCase("clear")) {
            if (args.length == 0) {
                pi.clear();
                p.sendMessage(ChatColor.GREEN + "Cleared Inventory.");
                return true;
            }
            if (args.length > 1) {
                p.sendMessage(ChatColor.RED + "Too many arguments.");
            }
            if (args.length == 1) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage(ChatColor.RED + "That user could not be found.");
                    return true;
                }
                PlayerInventory ti = target.getInventory();
                ti.clear();
                p.sendMessage(ChatColor.GREEN + "You have cleared the inventory of " + target.getName() + ".");
                target.sendMessage(ChatColor.GRAY + "Your inventory has been cleared.");
                return true;
            }

        }
        return true;
    }

}

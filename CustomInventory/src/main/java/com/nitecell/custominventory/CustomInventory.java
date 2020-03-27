package com.nitecell.custominventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class CustomInventory extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "[CustomInventory] has been enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[CustomInventory] has been disabled.");
    }

    public void createMenu(Player player) {

        Inventory inv = Bukkit.getServer().createInventory(null, 9, ChatColor.LIGHT_PURPLE + "Custom Inventory");
        ItemStack item1 = new ItemStack(Material.TNT);
        ItemMeta item1Meta = item1.getItemMeta();

        ArrayList<String> item1Lore = new ArrayList<String>();
        item1Lore.add(ChatColor.BLUE + "Test Lore!");
        item1Meta.setDisplayName(ChatColor.RED + "Test Name!");
        item1Meta.setLore(item1Lore);
        item1.setItemMeta(item1Meta);

    }


}
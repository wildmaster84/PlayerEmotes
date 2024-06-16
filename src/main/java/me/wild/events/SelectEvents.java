package me.wild.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import me.wild.PlayerEmotes;
import me.wild.menus.EmotesMenu;
import me.wild.menus.SelectMenu;
import me.wild.menus.TargetMenu;
public class SelectEvents implements Listener{
	
	@EventHandler
	public void onInventoryClicked(InventoryClickEvent event) {
		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
		if (event.getInventory().getHolder() instanceof SelectMenu) {
			PlayerEmotes main = PlayerEmotes.getInstance();
			event.setCancelled(true);
			ItemStack clickedItem = event.getCurrentItem();
			Player player = (Player)event.getWhoClicked();
			if (event.getClickedInventory().getHolder() instanceof SelectMenu) {
				if (clickedItem.getType() == Material.ARROW && event.getSlot() == 44) {
					main.targetPage.replace(player, main.targetPage.get(player) + 1);
					
					main.getEmotesMenu().setInventory(main.targetInv.get(player), main.targetPage.get(player));
			
				}
				if (clickedItem.getType() == Material.ARROW && event.getSlot() == 36) {
					main.targetPage.replace(player, main.targetPage.get(player) - 1);
					main.getEmotesMenu().setInventory(main.targetInv.get(player), main.targetPage.get(player));	
				}
	
				if (clickedItem.getType() == Material.STICK){
					if (main.emotesInv.get(player) == null) {
						main.emotesInv.put(player, Bukkit.createInventory(main.getEmotesMenu(), 5*9, "Emotes Menu"));
					}
					main.getEmotesMenu().setInventory(main.emotesInv.get(player), main.emotesPage.get(player));
					player.openInventory(main.getEmotesMenu().getInventory());
										
				}
				if (clickedItem.getType() == Material.BRICK){
					ItemMeta meta = clickedItem.getItemMeta();
					if (main.targetInv.get(player) == null) {
						main.targetInv.put(player, Bukkit.createInventory(main.getTargetsMenu(), 5*9, "Target Menu"));
					}
					if (main.targetPlayer.get(player) != null) {
						main.targetPlayer.remove(player);
					}
					main.getTargetsMenu().addItems(true);
					main.getTargetsMenu().setInventory(main.targetInv.get(player), main.targetPage.get(player));
					player.openInventory(main.getTargetsMenu().getInventory());
										
				}
			}
			return;
		}
		
	}
}

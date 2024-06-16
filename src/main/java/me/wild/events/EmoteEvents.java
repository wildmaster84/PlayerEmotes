package me.wild.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.wild.PlayerEmotes;
import me.wild.menus.EmotesMenu;
import net.md_5.bungee.api.ChatColor;
public class EmoteEvents implements Listener{
	
	@EventHandler
	public void onInventoryClicked(InventoryClickEvent event) {
		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
		if (event.getInventory().getHolder() instanceof EmotesMenu) {
			PlayerEmotes main = PlayerEmotes.getInstance();
			event.setCancelled(true);
			ItemStack clickedItem = event.getCurrentItem();
			Player player = (Player)event.getWhoClicked();
			if (event.getClickedInventory().getHolder() instanceof EmotesMenu) {
				if (clickedItem.getType() == Material.ARROW && event.getSlot() == 44) {
					main.emotesPage.replace(player, main.emotesPage.get(player) + 1);
					
					main.getEmotesMenu().setInventory(main.emotesInv.get(player), main.emotesPage.get(player));
			
				}
				if (clickedItem.getType() == Material.ARROW && event.getSlot() == 36) {
					main.emotesPage.replace(player, main.emotesPage.get(player) - 1);
					main.getEmotesMenu().setInventory(main.emotesInv.get(player), main.emotesPage.get(player));	
				}
	
				if (clickedItem.getType() == Material.PAPER){
					ItemMeta meta = clickedItem.getItemMeta();
					String targetMessage = meta.getPersistentDataContainer().get(new NamespacedKey(main, "target"), PersistentDataType.STRING);
					String selfMessage = meta.getPersistentDataContainer().get(new NamespacedKey(main, "self"), PersistentDataType.STRING);
					if (main.targetPlayer.get(player) != null && !targetMessage.isEmpty()) {
						Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', targetMessage.replace("{player}", player.getDisplayName()).replace("{target}", main.targetPlayer.get(player).getDisplayName())));
						player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, (float)1.0, (float)2.0);
						main.targetPlayer.get(player).playSound(main.targetPlayer.get(player).getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, (float)1.0, (float)2.0);
						main.targetPlayer.remove(player);
						player.closeInventory();
						return;
					}
					if (main.targetPlayer.get(player) == null & !selfMessage.isEmpty()) {
						Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', selfMessage.replace("{player}", player.getDisplayName())));
						player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, (float)1.0, (float)2.0);
						player.closeInventory();
						return;
					}
					
					
										
				}
			}
			return;
		}
		
	}
}

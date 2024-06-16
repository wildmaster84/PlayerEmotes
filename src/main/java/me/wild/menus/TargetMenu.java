package me.wild.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import me.wild.PlayerEmotes;

public class TargetMenu implements InventoryHolder {
	private List<ItemStack> emotes = new ArrayList<>();
	private Inventory inv;
	
	public TargetMenu() {
		
		addItems(false);
		
	}
	
	public void addItems(boolean clear) {
		if (clear) {
			emotes.clear();
		}
		if (emotes.isEmpty()) {
			
			for (Player player: Bukkit.getOnlinePlayers()) {
				ItemStack head = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta meta = (SkullMeta) head.getItemMeta();
				meta.setOwningPlayer(player);
				meta.setDisplayName(player.getDisplayName());
				head.setItemMeta(meta);
				emotes.add(head);
				
			} 
		}
	}

	private <T> List<List<T>> splitList(List<T> list, int size) {
        List<List<T>> sublists = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            int endIndex = Math.min(i + size, list.size());
            sublists.add(list.subList(i, endIndex));
        }
        return sublists;
    }

	private void setPageItems(int page) {
		inv.clear();
		
		ItemStack back = new ItemStack(Material.ARROW);
		ItemMeta meta = back.getItemMeta();
		meta.setDisplayName("Back");
		back.setItemMeta(meta);
		
		ItemStack next = new ItemStack(Material.ARROW);
		ItemMeta meta2 = next.getItemMeta();
		meta2.setDisplayName("Next");
		next.setItemMeta(meta2);
		if (!emotes.isEmpty()) {
			int pages = splitList(emotes, 36).size();
			if (pages >= 2 && page != pages - 1) {
				inv.setItem(44, next);
			}
			if (page >= 1) {
				inv.setItem(36, back);
			}
			
			splitList(emotes, 36).get(page).forEach(item -> {
				inv.addItem(item);
			});
		}
		
		//44 next page
		//36 last page
		
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inv;
	}
	
	public void setInventory(Inventory inv, int page) {
		// TODO Auto-generated method stub
		this.inv = inv;
		setPageItems(page);
	}
	
	public List<ItemStack> getItems() {
		// TODO Auto-generated method stub
		return emotes;
	}

}

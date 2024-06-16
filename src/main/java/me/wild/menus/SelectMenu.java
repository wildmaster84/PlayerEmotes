package me.wild.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class SelectMenu implements InventoryHolder {
	private Inventory inv;
	
	public SelectMenu() {
		inv = Bukkit.createInventory(this, 3*9, "Emotes");
		ItemStack self = new ItemStack(Material.STICK);
		ItemMeta meta = self.getItemMeta();
		meta.setDisplayName("Self");
		self.setItemMeta(meta);
		
		ItemStack target = new ItemStack(Material.BRICK);
		ItemMeta meta2 = target.getItemMeta();
		meta2.setDisplayName("Target");
		target.setItemMeta(meta2);
		
		
		inv.setItem(12, self);
		inv.setItem(14, target);
		
		//12 14
	}

	@Override
	public @NotNull Inventory getInventory() {
		// TODO Auto-generated method stub
		return inv;
	}

}

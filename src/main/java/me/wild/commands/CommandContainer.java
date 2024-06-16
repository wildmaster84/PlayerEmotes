package me.wild.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.wild.PlayerEmotes;
import net.md_5.bungee.api.ChatColor;

public class CommandContainer extends Command implements @Nullable CommandExecutor {
	List<String> aliases = new ArrayList<>();
	public CommandContainer(String name, JavaPlugin plugin) {
        super(name);
        this.setPermission("emotes." + name);
        this.setPermissionMessage("You do not have permission to use this command.");
    }

	@Override
    public boolean execute(CommandSender sender, String label, String[] args) {
		PlayerEmotes main = PlayerEmotes.getInstance();
		String self = getItemStack(main).getPersistentDataContainer().get(new NamespacedKey(main, "self"), PersistentDataType.STRING);
		String target = getItemStack(main).getPersistentDataContainer().get(new NamespacedKey(main, "target"), PersistentDataType.STRING);
		Player player1 = (Player) sender;
		if (args.length >= 1 && !target.isEmpty()) {
			if (Bukkit.getPlayer(args[0]) == null) {
				player1.sendMessage("This player is offline!");
				return true;
			}
        	Player player2 = Bukkit.getPlayer(args[0]);
        	Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format(target.replace("{player}", player1.getDisplayName()).replace("{target}", player2.getDisplayName()))));
        	player1.playSound(player1.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, (float)1.0, (float)2.0);
    		player2.playSound(player2.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, (float)1.0, (float)2.0);
    		
        } else {
        	Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format(self.replace("{player}", player1.getDisplayName()))));
        	player1.playSound(player1.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, (float)1.0, (float)2.0);
        }

        return true;
    }
	
	
	
	
	

	private ItemMeta getItemStack(PlayerEmotes main) {
		for (ItemStack item : main.getEmotesMenu().getItems()) {
			ItemMeta meta = item.getItemMeta();
			if (meta.getPersistentDataContainer().get(new NamespacedKey(main, "emote"), PersistentDataType.STRING).contains(getName())) {
				return meta;
			}
		}
		return null;
		
	}
	private ItemMeta getItemStack(PlayerEmotes main, String str) {
		for (ItemStack item : main.getEmotesMenu().getItems()) {
			ItemMeta meta = item.getItemMeta();
			if (meta.getPersistentDataContainer().get(new NamespacedKey(main, "emote"), PersistentDataType.STRING).contains(str)) {
				return meta;
			}
		}
		return null;
		
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		PlayerEmotes main = PlayerEmotes.getInstance();
        Player player = (Player) sender;
        switch(args.length) {
        case 0: {
        	player.openInventory(main.getSelectMenu().getInventory());
			return true;
        }
        case 2: {
        	Player player2 = Bukkit.getPlayer(args[1]);
        	String target = getItemStack(main, args[0]).getPersistentDataContainer().get(new NamespacedKey(main, "target"), PersistentDataType.STRING);
        	Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format(target.replace("{player}", player.getDisplayName()).replace("{target}", player2.getDisplayName()))));
        	return true;
        }
        default: {return false;}
        }
	}
}

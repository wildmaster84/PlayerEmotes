package me.wild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.wild.commands.CommandComplete;
import me.wild.commands.CommandContainer;
import me.wild.events.CoreEvents;
import me.wild.events.EmoteEvents;
import me.wild.events.SelectEvents;
import me.wild.events.TargetEvents;
import me.wild.menus.EmotesMenu;
import me.wild.menus.SelectMenu;
import me.wild.menus.TargetMenu;
import net.md_5.bungee.api.ChatColor;

public class PlayerEmotes extends JavaPlugin {
	
	private static PlayerEmotes instance;
	private static ConfigManager config;
	private static YamlConfiguration emotes;
	public HashMap<Player, Inventory> emotesInv = new HashMap<>();
	public HashMap<Player, Integer> emotesPage = new HashMap<>();
	public HashMap<Player, Inventory> targetInv = new HashMap<>();
	public HashMap<Player, Integer> targetPage = new HashMap<>();
	public HashMap<Player, Player> targetPlayer = new HashMap<>();
	private EmotesMenu emotesMenu;
	private TargetMenu targetMenu;
	private SelectMenu selectMenu;
	public List<String> commands = new ArrayList<>();
	
	public void onEnable() {
		instance = this;
		reloadConfigs();
		getCommand("emotes").setExecutor(new CommandContainer("emotes", instance));
		getCommand("emotes").setTabCompleter(new CommandComplete());
		getServer().getPluginManager().registerEvents(new CoreEvents(), this);
		getServer().getPluginManager().registerEvents(new EmoteEvents(), this);
		getServer().getPluginManager().registerEvents(new SelectEvents(), this);
		getServer().getPluginManager().registerEvents(new TargetEvents(), this);
		emotesMenu = new EmotesMenu();
		targetMenu = new TargetMenu();
		selectMenu = new SelectMenu();
		
		registerCommands();
	}
	
	
	private void reloadConfigs() {
		if (config == null) {
			config = new ConfigManager();
		}
		config.getConfig("emotes.yml");
		emotes = YamlConfiguration.loadConfiguration(config.getConfig("emotes.yml"));
	}


	public static PlayerEmotes getInstance() {
		return instance;
	}
	
	private void registerCommands() {
		for (ItemStack item: getEmotesMenu().getItems()) {
			commands.add(item.getItemMeta().getDisplayName());
			getServer().getCommandMap().register(ChatColor.stripColor(item.getItemMeta().getDisplayName()), new CommandContainer(ChatColor.stripColor(item.getItemMeta().getDisplayName()), instance));
		}
	}
	
	public static ConfigManager getConfigManager() {
		return config;
	}
	
	public YamlConfiguration getEmotes() {
		return emotes;
	}
	
	public EmotesMenu getEmotesMenu() {
		return emotesMenu;
	}
	public TargetMenu getTargetsMenu() {
		return targetMenu;
	}
	public SelectMenu getSelectMenu() {
		return selectMenu;
	}
	 
	 
	 

}

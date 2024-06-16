package me.wild.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.wild.PlayerEmotes;

public class CoreEvents implements Listener{
	

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		PlayerEmotes main = PlayerEmotes.getInstance();
		main.emotesPage.put(event.getPlayer(), 0);
		main.targetPage.put(event.getPlayer(), 0);
	}
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		PlayerEmotes main = PlayerEmotes.getInstance();
		main.emotesPage.remove(event.getPlayer());
		main.targetPage.remove(event.getPlayer());
	}
}

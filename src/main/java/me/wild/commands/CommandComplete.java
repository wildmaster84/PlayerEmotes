package me.wild.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.wild.PlayerEmotes;

public class CommandComplete implements TabCompleter {

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
			@NotNull String label, @NotNull String[] args) {
		
		switch(args.length ) {
			case 1:{return PlayerEmotes.getInstance().commands;}
			case 2:{
				List<String> players = new ArrayList<>();
				for (Player player: Bukkit.getOnlinePlayers()) {
					players.add(player.getName());
				}
				
				return players;
				}
			default: {return null;}
		}
	}

}

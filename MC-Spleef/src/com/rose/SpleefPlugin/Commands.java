package com.rose.SpleefPlugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{
	
	Game game;
	public Commands(Game game) {
		this.game = game;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("spleef")) {
				// join and add player to player list
				if(args[0].equalsIgnoreCase("join")) {
					player.sendMessage("You have joined.");
					this.game.player_join(player);
				}
				// starts listener for blocks being broken
				else if(args[0].equalsIgnoreCase("start")) {
					player.sendMessage("Spleef Start");
				}
				// deletes player from player list
				else if(args[0].equalsIgnoreCase("leave")) {
					player.sendMessage("You have left.");
					this.game.player_leave(player);
				}
				if(args[0].equalsIgnoreCase("create")) {
					player.sendMessage("Map Created");
					Location loc1 = new Location(player.getWorld(), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					Location loc2 = new Location(player.getWorld(), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
					this.game.create_floor(loc1, loc2);
				}
				if(args[0].equalsIgnoreCase("set")) {
					if(args[1].equalsIgnoreCase("start")) {
						Location start_loc = new Location(player.getWorld(), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
						this.game.start_loc = start_loc;
					} else if(args[1].equalsIgnoreCase("spectate")) {
						Location spectate_loc = new Location(player.getWorld(), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
						this.game.spectate_loc = spectate_loc;
					}
				}
				if(args[0].equalsIgnoreCase("getinfo")) {
					this.game.send_info(player);
				}
				return true;
			}
		}
        return false;
    }
}

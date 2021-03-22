package com.rose.SpleefPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Game {
	
	HashMap<Player, Location> player_list = new HashMap<Player,Location>();
	ArrayList<Player> players_in = new ArrayList<Player>();
	ArrayList<Player> players_out = new ArrayList<Player>();
	ArrayList<Block> floor = new ArrayList<Block>();
	int floor_level;
	
	Location start_loc;
	Location spectate_loc;
	
	void player_join(Player player) {
		this.player_list.put(player,player.getLocation());
		player.teleport(this.start_loc);
	}
	void player_leave(Player player) {
		this.player_list.remove(player);
		player.teleport(this.player_list.get(player));
	}
	
	void start() {
		this.players_in = new ArrayList<>(player_list.keySet());
	}
	
	int get_current_player_size() {
		return this.players_in.size();
	}
	
	void set_player_out(Player player) {
		players_in.remove(player);
		players_out.add(player);
		player.teleport(spectate_loc);
	}
	
	void update_players() {
		int players_left = this.players_in.size();
		int player_y;
		for(int i=0;i>players_left;i++) {
			Player player = this.players_in.get(i);
			player_y = (int)player.getLocation().getY();
			if(player_y < this.floor_level) {
				this.set_player_out(player);
			}
		}
	}
	
	void end_game() {
		int floor_size = this.floor.size();
		for(int i=0;i>floor_size;i++) {
			Block block = this.floor.get(i);
			block.setType(Material.SNOW_BLOCK);
		}
		player_list.clear();
		players_in.clear();
		players_out.clear();
	}
	
	void create_floor(Location loc1, Location loc2) {
		
		if(loc1.getY() != loc2.getY()) return;
		
        int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
        int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
 
        int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
        int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
        
        this.floor_level = (int)loc1.getY();
        
        for(int x = bottomBlockX; x <= topBlockX; x++)
        {
            for(int z = bottomBlockZ; z <= topBlockZ; z++)
            {
                Block block = loc1.getWorld().getBlockAt(x, this.floor_level, z);
                this.floor.add(block);
            }
        }
    }
	
	int get_floor_level() {
		return this.floor_level;
	}
	
	void send_info(Player player) {
		player.sendMessage("Start location: (" + start_loc.getX() + ", " + start_loc.getY() + ", " + start_loc.getZ() + ")");
		player.sendMessage("Spectate location: (" + spectate_loc.getX() + ", " + spectate_loc.getY() + ", " + spectate_loc.getZ() + ")");
		int floor_size = this.floor.size();
		for(int i=0;i>floor_size;i++) {
			Block block = this.floor.get(i);
			block.setType(Material.GOLD_BLOCK);
		}
		player.sendMessage("GOLD FLOOR !!!");
		String players = player_list.keySet().toArray()[0].toString();
		player.sendMessage("Players: " + players + ".");
	}
	
}

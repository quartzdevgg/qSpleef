package com.QarthO.Spleef.Game;

import org.bukkit.entity.Player;

import com.QarthO.Spleef.Arena.Arena;
import com.QarthO.Spleef.utils.Language;

import net.md_5.bungee.api.ChatColor;

public class GamePlayer {

	Player player;
	Arena arena;
	GamePlayerState state;
	
	public GamePlayer(Player player, Arena arena) {
		this.player = player;
		this.arena = arena;
	}
	
	public GamePlayerState getState() {
		return state;
	}
	
	public void setState(GamePlayerState state) {
		
		if(state == GamePlayerState.JOINING) {
			
			arena.player_join(player);
			player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.GREEN + "Joined " + ChatColor.YELLOW + arena.getName());
		}
		
		if(state == GamePlayerState.OUT) {
			
			arena.player_spec(player);
			player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.RED + "You're out! You'll auto-leave when the round is over");
		}
		
		if(state == GamePlayerState.PLAYING) {
			
			player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.GREEN + "You're now playing. Don't fall or you'll be out!");
		}
		
		if(state == GamePlayerState.SPEC) {
			
			arena.player_spec(player);
			player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.GREEN + "Spectating " + ChatColor.YELLOW + arena.getName() + ". You can type " + ChatColor.YELLOW + " /spleef leave " + ChatColor.RED + "to leave now");
		}
		
		this.state = state;
		
	}
}

package com.QarthO.Spleef.Game;

import org.bukkit.entity.Player;

import com.QarthO.Spleef.Arena.Arena;
import com.QarthO.Spleef.utils.Language;

import net.md_5.bungee.api.ChatColor;

public class GamePlayer {

	Player player;
	Arena arena;
	GamePlayerState state;
	
	public GamePlayer(Player player) {
		this.player = player;
	}
	
	public void setArena(Arena arena) {
		this.arena = arena;
	}
	
	public Arena getArena() {
		return arena;
	}
	
	public GamePlayerState getState() {
		return state;
	}
	

	public void setState(GamePlayerState state) {
		this.state = state;		
	}
	
	public void join(Arena arena) {
		setState(GamePlayerState.JOINING);
		setArena(arena);
		arena.player_join(player);
		player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.GREEN + "You've joined " + ChatColor.YELLOW + arena.getName());
	}
	
	public void leave() {
		setState(GamePlayerState.LEFT);
		arena.player_leave(player);
		player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.GREEN + "You've left " + ChatColor.YELLOW + arena.getName());
	
	}
	
	public void out() {
		setState(GamePlayerState.OUT);
		arena.player_spec(player);
		player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.RED + "You're out! You'll auto-leave when the round is over");
	}
	
	public void start() {
		setState(GamePlayerState.IN);
		player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.GREEN + "You're now playing. Don't fall or you'll be out!");
	}
	
	public void spec() {
		setState(GamePlayerState.SPEC);
		arena.player_spec(player);
		player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.GREEN + "Spectating " + ChatColor.YELLOW + arena.getName() + ". You can type " + ChatColor.YELLOW + " /spleef leave " + ChatColor.RED + "to leave now");
		
	}
	
	public String getName() {
		return player.getName();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public String toString() {
		
		String str = ChatColor.YELLOW + "(";
		str = str + player.getName() + ", ";
		str = str + state + ", ";
		str = str + arena.getName() + ")";
		
		return str + ChatColor.WHITE;
	}
}

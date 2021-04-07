package com.QarthO.Spleef.Game;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.entity.Player;

import com.QarthO.Spleef.Arena.Arena;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.utils.FormatMessage;

public class GamesManager {

	Set<Game> games;
	
	HashMap<Player, GamePlayer> playersMap;
	HashMap<String, Game> gamesMap;
	
	ArenasManager am;
	
	public GamesManager(ArenasManager am) {
		this.am = am;
		playersMap = new HashMap<Player, GamePlayer>();
		gamesMap = new HashMap<String, Game>();
	}
	
	public boolean isPlaying(Player player){
		return playersMap.containsKey(player);
	}
	
	public GamePlayer getGamePlayer(Player player) {
		return playersMap.get(player);
	}
	
	public Game getGame(Arena arena) {
		return gamesMap.get(arena.getName());
	}
	
	public boolean isActive(String arenaName) {
		return gamesMap.containsKey(arenaName);
	}

	public void join(Player player, Arena arena) {
		String arenaName = arena.getName();
		if(isPlaying(player)) {
			player.sendMessage(FormatMessage.error("Already in a game"));
			return;
		}
		
		if(!isActive(arenaName)){
			Game game = new Game(arena, playersMap);
			gamesMap.put(arenaName, game);
		} else {
			Game game = gamesMap.get(arenaName);
			game.addPlayer(player, GamePlayerState.JOINING);
		}
	}
	
}

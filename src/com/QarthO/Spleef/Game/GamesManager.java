package com.QarthO.Spleef.Game;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.entity.Player;

import com.QarthO.Spleef.Arena.Arena;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.utils.FormatMessage;

public class GamesManager {

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
	
	public boolean isActive(String arenaName) {
		return gamesMap.containsKey(arenaName);
	}
	
	public Game getGame(String arenaName) {
		Arena arena = am.getArena(arenaName);
		if(!isActive(arenaName)) {
			gamesMap.put(arenaName, new Game(arena));
		} 
		Game game = gamesMap.get(arenaName);
		return game;
		
	}
	
	public Collection<Game> getGames(){
		return gamesMap.values();
	}

	public void join(Player player, String arenaName) {
				
		if(isPlaying(player)) {
			player.sendMessage(FormatMessage.error("Already in a game"));
			return;
		}

		GamePlayer gPlayer = new GamePlayer(player);
		playersMap.put(player, gPlayer);

		Game game = getGame(arenaName);
		
		game.joinPlayer(gPlayer);
		
	}
	
	public void leave(Player player) {
		if(!isPlaying(player)) {
			player.sendMessage(FormatMessage.error("You're not in a game"));
			return;
		}
		GamePlayer gPlayer = playersMap.get(player);
		Game game = getGame(gPlayer.getArena().getName());
		game.leavePlayer(gPlayer);
		playersMap.remove(player);
	}
	
	public void start(String arenaName) {
		Game game = getGame(arenaName);
		game.start();
		
	}
}

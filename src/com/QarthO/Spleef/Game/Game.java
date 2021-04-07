package com.QarthO.Spleef.Game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

import com.QarthO.Spleef.Arena.Arena;

public class Game {

	Arena arena;
	Set<GamePlayer> players;
	GameState state;
	HashMap<Player, GamePlayer> playersMap;
	
	public Game(Arena arena, HashMap<Player, GamePlayer> playersMap) {
		this.arena = arena;
		this.playersMap = playersMap;
		
		arena.replaceFloor();
		this.state = GameState.READY;
	}
	
	public void start(){
		state = GameState.RUNNING;
		
	}
	
	public void end() {
		state = GameState.FINISHED;
	}
	
	public GameState getState() {
		return state;
	}
	
	public Set<Player> getPlayers() {
		Set<Player> players = new HashSet<Player>();
		for(GamePlayer gp : this.players) {
			players.add(gp.player);
		}
		return players;
	}
	
	public void addPlayer(Player player, GamePlayerState state) {
		GamePlayer gPlayer = new GamePlayer(player, arena);
		gPlayer.setState(state);
		players.add(gPlayer);
		playersMap.put(player, gPlayer);
	}
	
	
}

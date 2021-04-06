package com.QarthO.Spleef.Game;

import java.util.Set;

import com.QarthO.Spleef.Arena.Arena;

public class Game {

	Arena arena;
	Set<GamePlayer> players;
	GameState state;
	
	public Game(Arena arena) {
		this.arena = arena;
		arena.replaceFloor();
		this.state = GameState.READY;
	}
	
	
	
}

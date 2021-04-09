package com.QarthO.Spleef.Game;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.QarthO.Spleef.Arena.Arena;
import com.QarthO.Spleef.utils.Language;

import net.md_5.bungee.api.ChatColor;

public class Game {

	Arena arena;
	Set<GamePlayer> players;
	GameState state;
	GamePlayer winner;
	public Game(Arena arena) {
		this.arena = arena;
		this.players = new HashSet<GamePlayer>();
		
		setup();
	}
	
	public void start(){
		setState(GameState.RUNNING);
		for(GamePlayer gPlayer : players) {
			gPlayer.start();
		}
	}
	
	public void end() {
		setState(GameState.FINISHED);
		if(winner != null) {
			broadcast("Congrats to " + winner.getName());
		}
	}
	
	public void clear() {
		setState(GameState.IDLE);
		for(GamePlayer gPlayer : players) {
			gPlayer.leave();
		}
		setup();
	}
	
	public Set<Block> getFloor(){
		return arena.getFloor();
	}
	
	public void setup() {
		arena.replaceFloor();
		if(players != null)
			players.clear();
		state = GameState.READY;
		
	}
	
	public void setState(GameState state) {
		
		if(state == GameState.FINISHED) {
			broadcast(ChatColor.GREEN + "Congrats " + ChatColor.YELLOW + players);
		}
		
		
		
		this.state = state;
	}
	
	public GameState getState() {
		return state;
	}
	
	public int getFloorLevel() {
		return arena.getFloorLevel();
	}
	
	public Set<Player> getPlayers() {
		Set<Player> players = new HashSet<Player>();
		for(GamePlayer gp : this.players) {
			players.add(gp.player);
		}
		return players;
	}
	
	public HashSet<GamePlayer> getInPlayers(){
		return getStatePlayers(GamePlayerState.IN);
	}
	
	public HashSet<GamePlayer> getStatePlayers(GamePlayerState state){
		HashSet<GamePlayer> statePlayers = new HashSet<GamePlayer>();
		
		for(GamePlayer gPlayer : players) {
			if(gPlayer.getState() == state){
				statePlayers.add(gPlayer);
			}
		}
		
		return statePlayers;
	}
	
	public void addPlayer(GamePlayer gPlayer) {
		players.add(gPlayer);
	}
	
	public void leavePlayer(GamePlayer gPlayer) {
		players.remove(gPlayer);
		gPlayer.leave();
		
		if(players.size() == 0)
			setState(GameState.FINISHED);
	}
	
	public void joinPlayer(GamePlayer gPlayer) {
		if(!players.contains(gPlayer))
			players.add(gPlayer);
		gPlayer.join(arena);
	}
	
	public void specPlayer(GamePlayer gPlayer) {
		if(!players.contains(gPlayer))
			players.add(gPlayer);
		gPlayer.spec();
	}
	
	public void outPlayer(GamePlayer gPlayer) {
		gPlayer.out();
		int playerInSize = getInPlayers().size();
		if(playerInSize < 2)
			if(playerInSize == 1)
				getInPlayers().forEach(n -> winner = n);
			end();
		

	}
	
	public void broadcast(String msg) {
		for(Player player : getPlayers()) {
			player.sendMessage(Language.CHAT_PREFIX.getMessage() + msg);
		}
	}
	
	public void updatePlayer(GamePlayer gPlayer, GamePlayerState state) {
		gPlayer.setState(state);
	}
	
	@Override
	public String toString() {
		String line1 = "Arena: " + arena.getName();
		String line2 = "GameState: " + state;
		String line3 = "Players: " + players;
		
		String str = line1 + "\n" + Language.CHAT_PREFIX.getMessage() + line2 + "\n" + Language.CHAT_PREFIX.getMessage() + line3;
		
		return str;
	}
}

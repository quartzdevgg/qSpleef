package com.QarthO.Spleef.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.QarthO.Spleef.Game.Game;
import com.QarthO.Spleef.Game.GamePlayer;
import com.QarthO.Spleef.Game.GameState;
import com.QarthO.Spleef.Game.GamesManager;

public class PlayerMoveListener implements Listener{
	
	GamesManager gm;
	
	public PlayerMoveListener(GamesManager gm) {
		this.gm = gm;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onMove(PlayerMoveEvent event) {
		for(Game game: gm.getGames()) {
			if(game.getState() == GameState.RUNNING) {
				for(GamePlayer gPlayer : game.getInPlayers()) {
					Player player = gPlayer.getPlayer();
					if(player.getLocation().getY() < game.getFloorLevel()) {
						game.outPlayer(gPlayer);
					}
				}
			}
		}
	}
	
}

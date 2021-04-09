package com.QarthO.Spleef.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.QarthO.Spleef.Game.Game;
import com.QarthO.Spleef.Game.GameState;
import com.QarthO.Spleef.Game.GamesManager;

public class BlockBreakListener implements Listener{
	
	GamesManager gm;
	
	public BlockBreakListener(GamesManager gm) {
		this.gm = gm;
	}

	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block eventBlock = event.getBlock();
				
		for(Game game: gm.getGames()) {
			for(Block gameBlock: game.getFloor()) {
				if(eventBlock.equals(gameBlock)) {
					if(game.getState() != GameState.RUNNING) {
						event.setCancelled(true);
						player.sendMessage("Wait for the game to start");
					}
				}
			}
		}
		
	}
}

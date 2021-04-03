package com.QarthO.Spleef.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.QarthO.Spleef.Arena.ArenasManager;

public class BlockBreakListener implements Listener{
	
	ArenasManager arenas;
	
	public BlockBreakListener(ArenasManager arenas) {
		this.arenas = arenas;
	}

	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		player.getAddress();
				
	}
}

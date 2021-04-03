package com.QarthO.Spleef.listeners;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.QarthO.Spleef.Arena.ArenaEditor;
import com.QarthO.Spleef.Arena.ArenasManager;

public class PlayerInteractListener implements Listener{
	
	ArenasManager arenas;
	
	public PlayerInteractListener(ArenasManager arenas) {
		this.arenas = arenas;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if(event.getHand() != EquipmentSlot.HAND) return;
		if(!arenas.isEditor(player)) return;
		
		ArenaEditor editor = arenas.getEditor(player);
		Location loc = event.getClickedBlock().getLocation();
		
		arenas.finishEdit(editor, loc);
		
			
	}
	
}

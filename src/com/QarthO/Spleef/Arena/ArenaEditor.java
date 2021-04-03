package com.QarthO.Spleef.Arena;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.QarthO.Spleef.utils.Language;

public class ArenaEditor {
		
	Player player;
	Arena arena;
	boolean creating;
	EditorStep step;
	
	public ArenaEditor(Player editor, Arena arena, EditorStep step, boolean creating) {
		this.player = editor;
		this.arena = arena;
		this.creating = creating;
		this.step = step;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Arena getArena() {
		return arena;
	}
	
	public EditorStep getStep() {
		return step;
	}
	
	public void setStep(EditorStep step) {
		this.step = step;
	}
	
	public void nextStep() {
		step = step.nextStep();
	}
		
	public boolean isCreating() {
		return creating;
	}
	
	public void sendFinishStatus(Location loc) {
		String msg = step.getMessageFinish();
		msg = msg.replaceAll("%loc", Language.LOCATION.loc(loc));
		msg = msg.replaceAll("%arena", arena.getName());
		if(creating) msg = msg.replaceAll("%f", "created");
		else msg = msg.replaceAll("%f", "updated");
		
		player.sendMessage(msg);
	}
	
	public void sendStartStatus() {
		String msg = step.getMessageStart();
		msg = msg.replaceAll("%arena", arena.getName());
		if(msg.contains("%status")) {
			player.sendMessage(arena.toString());;
		} else {
			player.sendMessage(msg);
		}
	}
	
	
	@Override
	public String toString() {
		String arenaName = "";
		if(arena == null) arenaName = "null";
		else arenaName = arena.getName();
		return "(" + player.getName() + ", " + arenaName + ", " + step + ", " + creating + ")";
	}
	
	
	
	
	
}

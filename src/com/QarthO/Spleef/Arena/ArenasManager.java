package com.QarthO.Spleef.Arena;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.QarthO.Spleef.utils.Storage;

public class ArenasManager {	
	
	Storage storage;
	Set<Arena> arenas;
	Set<ArenaEditor> editors = new HashSet<ArenaEditor>();

	
	public ArenasManager(Storage storage) {
		this.storage = storage;
	}
	
	
	public void createArena(Player player, String arenaName) {
		Arena arena = new Arena(arenaName, player.getWorld(), storage.getDefaultFloorMaterial());
		this.addEditor(player, arena, EditorStep.LOC1, true);
		this.getEditor(player).sendStartStatus();
		storage.saveArena(arena);
		
	}
	
	public void editArena(Player player, Arena arena, EditorStep step) {
		this.addEditor(player, arena, step, false);
	}
	
	public void finishEdit(ArenaEditor editor, Location loc) {
		Arena arena = editor.getArena();
		EditorStep step = editor.getStep();
		
		if(step == EditorStep.LOC1) arena.setLoc1(loc);
		if(step == EditorStep.LOC2) arena.setLoc2(loc);
		if(step == EditorStep.JOIN) arena.setJoinLoc(loc);
		if(step == EditorStep.SPEC) arena.setSpecLoc(loc);
		
		editor.sendFinishStatus(loc);
		editor.nextStep();
		if((editor.creating || step == EditorStep.LOC2) && editor.getStep() != EditorStep.COMPLETED) {
			editor.sendStartStatus();
		}
		if(editor.getStep() == EditorStep.COMPLETED) {
			editor.sendFinishStatus(loc);
			editors.remove(editor);
		}
		
		this.save(arena);
	}
	
	public void save(Arena arena) {
		storage.saveArena(arena);	
	}
	
	public void delete(String arenaName) {
		storage.deleteArena(arenaName);
	}
	
	public boolean exists(String name) {
		return storage.exists(name);
	}
	
	public Set<String> getArenaList() {
		return storage.getArenaList();
	}
	
	public Set<ArenaEditor> getEditors(){
		return editors;
	}
	
	public ArenaEditor getEditor(Player player) {
		for(ArenaEditor editor : editors) {
			if(editor.getPlayer() == player) return editor;
		}
		return null;
		
	}
	
	public boolean isEditor(Player player) {
		return (this.getEditor(player) != null);
	}
	
	public void addEditor(Player player, Arena arena, EditorStep step, Boolean creating) {	
		ArenaEditor editor = new ArenaEditor(player, arena, step, creating);
		editors.add(editor);
	}
	
	public void removeEditor(ArenaEditor editor) {
		editors.remove(editor);
	}
	
}

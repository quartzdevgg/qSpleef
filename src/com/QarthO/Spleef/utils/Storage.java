package com.QarthO.Spleef.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.QarthO.Spleef.Main;
import com.QarthO.Spleef.Arena.Arena;

public class Storage {

	public FileConfiguration arenasCfg;
	public File arenasFile;
	FileConfiguration config;
	
	
	public Storage(Main plugin) {
		
		this.config = plugin.getConfig();
		config.options().copyDefaults(true);
		plugin.saveConfig();
		
		arenasFile = new File(plugin.getDataFolder(), "qArenas.yml");
		
		if(!arenasFile.exists()) {
			try {
				arenasFile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage(Language.CHAT_PREFIX.getMessage() + Language.ARENA_YML.getMessage());
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(Language.CHAT_PREFIX.getMessage() + Language.ERROR_FAILED_ARENA_YML_CREATE.getMessage());
			}
		}
		
		arenasCfg = YamlConfiguration.loadConfiguration(arenasFile);
		
	}
	
	public void saveArena(Arena arena) {
		String name = arena.getName();
		if(name == null) {
			Bukkit.getServer().getConsoleSender().sendMessage(Language.CHAT_PREFIX.getMessage() + Language.ERROR_FAILED_ARENA_YML_SAVE.getMessage());
			return;
		}
		
		try {
			World world = arena.getWorld();
			if(world != null) arenasCfg.set(name + ".world", world.getName());
			
			Material material = arena.getFloorType();
			if(material != null) arenasCfg.set(name + ".floor-material", material.name());
			else arenasCfg.set(name + ".floor-material", this.getDefaultFloorMaterial());
			
			Location loc = arena.getLoc1();
			saveLoc(loc, name + ".zone.loc1");
			
			loc = arena.getLoc2();
			saveLoc(loc, name + ".zone.loc2");
			
			loc = arena.getJoinLoc();
			saveLoc(loc, name + ".join");
			loc = arena.getSpecLoc();
			saveLoc(loc, name + ".spec");
			
			arenasCfg.save(arenasFile);
		} catch(IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(Language.CHAT_PREFIX.getMessage() + Language.ERROR_FAILED_ARENA_YML_SAVE.getMessage());
		}
	}
	
	public void deleteArena(String name) {
		arenasCfg.set(name, null);
		try {
			arenasCfg.save(arenasFile);
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(Language.CHAT_PREFIX.getMessage() + Language.ERROR_FAILED_ARENA_YML_SAVE.getMessage());
		}
	}
	
	public Arena loadArena(String name) {
		if(!this.exists(name)) return null;
		
		World world = Bukkit.getWorld((String) arenasCfg.get(name + ".world"));
		Material material = Material.getMaterial((String) arenasCfg.get(name + ".floor-material"));
		Arena arena = new Arena(name, world, material);
		
		arena.setFloorType(material);
		
		Location loc;
		
		loc = this.loadLoc(world, name + ".zone.loc1");
		arena.setLoc1(loc);
		
		loc = this.loadLoc(world, name + ".zone.loc2");
		arena.setLoc2(loc);

		loc = this.loadLoc(world, name + ".join");
		arena.setSpecLoc(loc);

		loc = this.loadLoc(world, name + ".spec");
		arena.setJoinLoc(loc);
		
		arena.create_floor();
		
		return arena;
	}
	
	public boolean exists(String name) {
		for(String arenaName : this.getArenaList()) {
			if(arenaName.equalsIgnoreCase(name)) return true;
		}
		return false;
	}
	
	public Set<String> getArenaList(){
		Set<String> keys = arenasCfg.getKeys(false);
		Set<String> arenaList = new HashSet<String>();
		for(String key : keys) {
			if(!key.contains(".")) {
				arenaList.add(key);
			}
			
		}
		return arenaList;
	}
	
	public void saveLoc(Location loc, String path) {
		if(loc == null) return;
		arenasCfg.set(path + ".x", loc.getX());
		arenasCfg.set(path + ".y", loc.getY());
		arenasCfg.set(path + ".z", loc.getZ());
	}
	
	public Location loadLoc(World world, String path) {
		Location loc = new Location(world, 0, 0, 0);
		loc.setX(arenasCfg.getDouble(path + ".x"));
		loc.setY(arenasCfg.getDouble(path + ".y"));
		loc.setZ(arenasCfg.getDouble(path + ".z"));
		return loc;
	}
	
	public Material getDefaultFloorMaterial() {
		Material defaultFloorType = Material.getMaterial((String) config.get("default.floor-material"));
		return defaultFloorType;
	}
	
	
}

package com.QarthO.Spleef;

import org.bukkit.plugin.java.JavaPlugin;

import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Commands.CommandsManager;
import com.QarthO.Spleef.listeners.BlockBreakListener;
import com.QarthO.Spleef.listeners.PlayerInteractListener;
import com.QarthO.Spleef.utils.Storage;

public class Main extends JavaPlugin{
	
	private ArenasManager arenas;
	private PlayerInteractListener playerInteractListener;
	private BlockBreakListener blockBreakListener;
	private Storage storage;
	
    @Override
    public void onEnable() {
    	storage = new Storage(this);
    	arenas = new ArenasManager(storage);
    	playerInteractListener = new PlayerInteractListener(arenas);
    	blockBreakListener = new BlockBreakListener(arenas);
    	
    	this.getCommand("spleef").setExecutor(new CommandsManager(arenas));
    	this.getServer().getPluginManager().registerEvents(blockBreakListener, this);
    	this.getServer().getPluginManager().registerEvents(playerInteractListener, this);
    	
    	getConfig().options().copyDefaults(true);
    	saveConfig();
    }
 
    @Override
    public void onDisable() {
    	
    }
}

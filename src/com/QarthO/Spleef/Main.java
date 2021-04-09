package com.QarthO.Spleef;


import org.bukkit.plugin.java.JavaPlugin;

import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Game.GamesManager;
import com.QarthO.Spleef.listeners.BlockBreakListener;
import com.QarthO.Spleef.listeners.PlayerInteractListener;
import com.QarthO.Spleef.listeners.PlayerMoveListener;

public class Main extends JavaPlugin{
	
	private ArenasManager am;
	private GamesManager gm; 
	private PlayerInteractListener playerInteractListener;
	private BlockBreakListener blockBreakListener;
	private PlayerMoveListener playerMoveListener;
	
	
    @Override
    public void onEnable() {
    	am = new ArenasManager();
    	gm = new GamesManager(am);
    	playerInteractListener = new PlayerInteractListener(am);
    	blockBreakListener = new BlockBreakListener(gm);
    	playerMoveListener = new PlayerMoveListener(gm);
    	    	
    	this.getCommand("spleef").setExecutor(new CommandsManager(am, gm));
    	this.getServer().getPluginManager().registerEvents(blockBreakListener, this);
    	this.getServer().getPluginManager().registerEvents(playerInteractListener, this);
    	this.getServer().getPluginManager().registerEvents(playerMoveListener, this);
    	getConfig().options().copyDefaults(true);
    	saveConfig();
    }
 
    @Override
    public void onDisable() {
    	
    }
}

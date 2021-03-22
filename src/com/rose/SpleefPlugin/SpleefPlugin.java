package com.rose.SpleefPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public class SpleefPlugin extends JavaPlugin{
	
	private Game game;
	
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	game = new Game();
    	this.getCommand("spleef").setExecutor(new Commands(game));
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	
    }
   
}

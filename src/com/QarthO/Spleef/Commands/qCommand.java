package com.QarthO.Spleef.Commands;

import java.util.List;

import org.bukkit.entity.Player;

import com.QarthO.Spleef.CommandsManager;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Game.GamesManager;
import com.QarthO.Spleef.utils.PluginYML;

abstract public class qCommand {
	
	String perms;
	String name;
	String syntax;
	PluginYML pluginYML;
	
	CommandsManager cm;
	ArenasManager am;
	GamesManager gm;
	
	public qCommand(CommandsManager cm, ArenasManager am, GamesManager gm) {
		this.pluginYML = new PluginYML();
		this.cm = cm;
		this.am = am;
	}
	
	public abstract void run(Player player, String[] args);
	
	public abstract List<String> getTabs(Player player, String[] args);
	
}

package com.QarthO.Spleef.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.QarthO.Spleef.CommandsManager;
import com.QarthO.Spleef.Arena.Arena;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Game.GamesManager;
import com.QarthO.Spleef.utils.Language;
import com.QarthO.Spleef.utils.FormatMessage;

public class Commandjoin extends qCommand {

	GamesManager gm;
	public Commandjoin(CommandsManager cm, ArenasManager am, GamesManager gm) {
		super(cm, am, gm);
		this.gm = gm;
		name = "spectate";
		perms = "spleef.player.join";
		syntax = "/spleef join <arena_name>";
	}

	@Override
	public void run(Player player, String[] args) {
		
		if(!player.hasPermission(perms)) {
			player.sendMessage(Language.ERROR_NO_PERMISSIONS.getMessage());
			return;
		}
		if(args.length != 2) {
			player.sendMessage(FormatMessage.syntax(syntax));
			return;
		}
		
		String arenaName = args[1];
		
		if(!am.exists(arenaName)) {
			player.sendMessage(ChatColor.RED + "Error: Arena " + ChatColor.YELLOW + arenaName + ChatColor.RED + " doesn't exist!" );
			return;
		} 
		
		Arena arena = am.getArena(arenaName);
		gm.join(player, arena);
		
	}

	@Override
	public List<String> getTabs(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

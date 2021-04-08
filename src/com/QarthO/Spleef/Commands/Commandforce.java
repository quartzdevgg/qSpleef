package com.QarthO.Spleef.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.QarthO.Spleef.CommandsManager;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Game.GamesManager;
import com.QarthO.Spleef.utils.Language;
import com.QarthO.Spleef.utils.FormatMessage;

public class Commandforce extends qCommand {

	
	public Commandforce(CommandsManager cm, ArenasManager am, GamesManager gm) {
		super(cm, am, gm);
		name = "spectate";
		perms = "spleef.admin.info";
		syntax = "/spleef force <start/end> <arena_name>";
	}

	@Override
	public void run(Player player, String[] args) {
		if(!player.hasPermission(perms)) {
			player.sendMessage(Language.ERROR_NO_PERMISSIONS.getMessage());
			return;
		}
		if(args.length != 3) {
			player.sendMessage(FormatMessage.syntax(syntax));
			return;
		}
		
		if(!args[1].equalsIgnoreCase("start") || args[1].equalsIgnoreCase("stop")) {
			player.sendMessage(FormatMessage.syntax(syntax));
			return;
		}
		
		String arenaName = args[2];
		
		if(!am.exists(arenaName)) {
			player.sendMessage(FormatMessage.error("Arena " + ChatColor.YELLOW + arenaName + ChatColor.RED + " doesn't exist!"));
			return;
		}
		
		if(args[1].equalsIgnoreCase("start")){
			gm.start(arenaName);
		}
		
		if(args[1].equalsIgnoreCase("stp[")){
			//gm.stop(arenaName);
		}

		player.sendMessage(Language.CHAT_PREFIX.getMessage() + "Force " + args[1] + "ing " + arenaName);
			
	}

	@Override
	public List<String> getTabs(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

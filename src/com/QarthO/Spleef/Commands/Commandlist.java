package com.QarthO.Spleef.Commands;

import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.QarthO.Spleef.CommandsManager;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Game.GamesManager;
import com.QarthO.Spleef.utils.Language;
import com.QarthO.Spleef.utils.FormatMessage;

public class Commandlist extends qCommand {

	public Commandlist(CommandsManager cm, ArenasManager am, GamesManager gm) {
		super(cm, am, gm);
		name = "list";
		perms = "spleef.player.list";
		syntax = "/spleef list";
	}

	@Override
	public void run(Player player, String[] args) {
		
		if(!player.hasPermission(perms)) {
			player.sendMessage(Language.ERROR_NO_PERMISSIONS.getMessage());
			return;
		}
		if(args.length != 1) {
			player.sendMessage(FormatMessage.syntax(syntax));
			return;
		}
		
		Set<String> arenaList = am.getArenaList();
		player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.GREEN + "Arenas: " + ChatColor.YELLOW + arenaList);
		
	}

	@Override
	public List<String> getTabs(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

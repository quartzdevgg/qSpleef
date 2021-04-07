package com.QarthO.Spleef.Commands;

import java.util.List;

import org.bukkit.entity.Player;

import com.QarthO.Spleef.CommandsManager;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Game.GamesManager;
import com.QarthO.Spleef.utils.Language;
import com.QarthO.Spleef.utils.FormatMessage;

public class Commandhelp extends qCommand {
	
	public Commandhelp(CommandsManager cm, ArenasManager am, GamesManager gm) {
		super(cm, am, gm);
		name = "help";
		perms = "spleef.player.help";
		syntax = "/spleef help";
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
		
		player.sendMessage(Language.BORDER_TOP.getMessage());
		
		player.sendMessage(Language.HELP.getMessage());
		
		if(player.hasPermission("spleef.mod.help"))
			player.sendMessage(Language.HELP_MOD.getMessage());
		if(player.hasPermission("spleef.admin.help"))
			player.sendMessage(Language.HELP_ADMIN.getMessage());		

		player.sendMessage(Language.BORDER_BOTTOM.getMessage());

	}

	@Override
	public List<String> getTabs(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

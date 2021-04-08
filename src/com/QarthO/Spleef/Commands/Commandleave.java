package com.QarthO.Spleef.Commands;

import java.util.List;

import org.bukkit.entity.Player;

import com.QarthO.Spleef.CommandsManager;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Game.GamesManager;
import com.QarthO.Spleef.utils.Language;
import com.QarthO.Spleef.utils.FormatMessage;

public class Commandleave extends qCommand {
	
	public Commandleave(CommandsManager cm, ArenasManager am, GamesManager gm) {
		super(cm, am, gm);
		name = "spectate";
		perms = "spleef.player.leave";
		syntax = "/spleef leave";
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

		if(!gm.isPlaying(player)) {
			player.sendMessage(FormatMessage.error("You're not in a game"));
			return;
		}
		//Arena arena 
		
		gm.leave(player);
		
	}

	@Override
	public List<String> getTabs(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

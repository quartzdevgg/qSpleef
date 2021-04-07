package com.QarthO.Spleef.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.QarthO.Spleef.CommandsManager;
import com.QarthO.Spleef.Arena.Arena;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Game.Game;
import com.QarthO.Spleef.Game.GamesManager;
import com.QarthO.Spleef.utils.Language;
import com.QarthO.Spleef.utils.FormatMessage;

public class Commandinfo extends qCommand {

	GamesManager gm;
	
	public Commandinfo(CommandsManager cm, ArenasManager am, GamesManager gm) {
		super(cm, am, gm);
		this.gm = gm;
		name = "spectate";
		perms = "spleef.admin.info";
		syntax = "/spleef info <arena_name>";
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
			player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.RED + "Error: Arena " + ChatColor.YELLOW + arenaName + ChatColor.RED + " doesn't exist!" );
			return;
		} 
		
		String msg = arenaName + "(%state, %players)";
		Game game;
		
		Arena arena = am.getArena(arenaName);
		if(gm.isActive(arenaName)) {
			game = gm.getGame(arena);
			msg = msg.replaceAll("%players", "" + game.getPlayers());
			
		} else {
			player.sendMessage("not active :)");
			game = new Game(arena, null);
		}

		msg = msg.replaceAll("%state", "" + game.getState());
		
		player.sendMessage(Language.CHAT_PREFIX.getMessage() + msg);
		
	}

	@Override
	public List<String> getTabs(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

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

public class Commandspectate extends qCommand {

	public Commandspectate(CommandsManager cm, ArenasManager am, GamesManager gm) {
		super(cm, am, gm);
		name = "spectate";
		perms = "spleef.player.spectate";
		syntax = "/spleef spectate <arena_name>";
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
		arena.player_spec(player);
		player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.GREEN + "Now spectating " + ChatColor.YELLOW + arenaName);
		
	}

	@Override
	public List<String> getTabs(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

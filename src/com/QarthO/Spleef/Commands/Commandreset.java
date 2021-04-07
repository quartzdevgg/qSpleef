package com.QarthO.Spleef.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.QarthO.Spleef.CommandsManager;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.Game.GamesManager;
import com.QarthO.Spleef.utils.Language;
import com.QarthO.Spleef.utils.FormatMessage;

public class Commandreset extends qCommand {

	public Commandreset(CommandsManager cm, ArenasManager am, GamesManager gm) {
		super(cm, am, gm);
		name = "reset";
		perms = "spleef.admin.reset";
		syntax = "/spleef reset <arena_name>";
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
		
		am.getArena(arenaName).replaceFloor();
		player.sendMessage(Language.CHAT_PREFIX.getMessage() + ChatColor.GREEN + "Arena " + ChatColor.YELLOW + arenaName + ChatColor.GREEN + "'s floor reset");
		
	}

	@Override
	public List<String> getTabs(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

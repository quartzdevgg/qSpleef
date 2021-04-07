package com.QarthO.Spleef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;

import com.QarthO.Spleef.Commands.CommandNotFoundException;
import com.QarthO.Spleef.Commands.qCommand;
import com.QarthO.Spleef.Game.GamesManager;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.utils.Language;




public class CommandsManager implements TabExecutor{
		
	
	
	ArenasManager am;
	GamesManager gm;
	
	public CommandsManager(ArenasManager am, GamesManager gm) {
		this.am = am;
		this.gm = gm;
	}
	
	public void loadCommand(Player player, String[] args) throws CommandNotFoundException {
		Class<?> loaded;
		qCommand qcommand;
		try {
			loaded = Main.class.getClassLoader().loadClass("com.QarthO.Spleef.Commands.Command" + args[0].toLowerCase());
			qcommand = (qCommand) loaded.getConstructor(CommandsManager.class, ArenasManager.class, GamesManager.class).newInstance(this, am, gm);
		} catch (Exception e) {
			throw(new CommandNotFoundException(args[0]));
		}
		qcommand.run(player, args);
	}
	
	public void loadTabs(Player player, String[] args) throws CommandNotFoundException {
		Class<?> loaded;
		qCommand qcommand;
		try {
			loaded = Main.class.getClassLoader().loadClass("com.QarthO.Spleef.Commands.Command" + args[0].toLowerCase());
			qcommand = (qCommand) loaded.getConstructor(CommandsManager.class, ArenasManager.class, GamesManager.class).newInstance(this, am, gm);
		} catch (Exception e) {
			throw(new CommandNotFoundException(args[0]));
		}
		
		qcommand.getTabs(player, args);
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		// Checks if send is a player and not the console
		
		if(!(sender instanceof Player)){
			sender.sendMessage(Language.ERROR_CONSOLE_MESSAGE.getMessage());
			return false;
		}
		
		Player player = (Player) sender;
		
		// Verifies this plugin's command
		
		if(args.length == 0) {
			player.sendMessage(Language.CMD_INFO.getMessage());
			return false;
		}
		
		if(args.length > 0) {
			try {
				loadCommand(player, args);
				return true;
			}
			catch(CommandNotFoundException e) {
				player.sendMessage(e.getMessage());
				return false;
			}
		}
		
		return false;
		
		
    }

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if(!label.equalsIgnoreCase("spleef")) return null;
                
        String[] cmdsNeedArenaList = {"join", "getinfo", "set", "spec", "spectate", "reset", "delete"};
        List<String> test = Arrays.asList(cmdsNeedArenaList);
        
        if(args.length == 2) {
        	if(test.contains(args[0])) {
            	am.getArenaList().forEach((n) -> commands.add(n));
                StringUtil.copyPartialMatches(args[1], commands, completions);
            }
        }
        
        Collections.sort(completions);
        return completions;
	}
}


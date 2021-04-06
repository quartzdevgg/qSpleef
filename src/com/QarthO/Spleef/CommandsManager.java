package com.QarthO.Spleef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import com.QarthO.Spleef.Arena.Arena;
import com.QarthO.Spleef.Arena.ArenasManager;
import com.QarthO.Spleef.utils.Language;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;




public class CommandsManager implements TabExecutor{
	
	Set<Command> set = new HashSet<Command>();
	
	
	String[] CMDs = {
			"help", "helpmod", "helpadmin", "join", "leave", "spectate", "spec", "list", "create", "delete", "start", "set", "reset", "getinfo", "debug"
			};
	
	String project_name = "Spleef";
	String cmd_label = "spleef";
	String version = "1.0.0";
	
	ArenasManager arenas;
	
	public CommandsManager(ArenasManager arenas) {
		this.arenas = arenas;
		
		
		
		
		
	}
	
	private boolean isCommand(String cmd){
		for(String command : CMDs){
			if(cmd.equalsIgnoreCase(command)){
				return true;
			}
		}
		return false;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		// Checks if send is a player and not the console
		
		if(!(sender instanceof Player)){
			sender.sendMessage(Language.ERROR_CONSOLE_MESSAGE.getMessage());
			return false;
		}
		
		Player player = (Player) sender;
		
		// Verifies is this plugin's command
		
		if(commandLabel.equalsIgnoreCase(cmd_label)){
			if(args.length == 0){
				player.sendMessage(Language.CMD_INFO.getMessage());
				return false;
			}
		}
		
		// Checks if valid command
		
		if(!(isCommand(args[0]))) {
			player.sendMessage(Language.ERROR_UKNOWN_COMMAND.getMessage());
			return false;
		}
		
		
		
		//	Help menus
		
		if(args[0].equalsIgnoreCase("help")) {
			player.sendMessage(Language.BORDER_TOP.getMessage());
			player.sendMessage(Language.HELP.getMessage());
			player.sendMessage(Language.BORDER_BOTTOM.getMessage());
			return true;
		}
		
		if(args[0].equalsIgnoreCase("helpmod")) {
			player.sendMessage(Language.BORDER_TOP.getMessage());
			player.sendMessage(Language.HELP_MOD.getMessage());
			player.sendMessage(Language.BORDER_BOTTOM.getMessage());
			return true;
		}
		
		if(args[0].equalsIgnoreCase("helpadmin")) {
			player.sendMessage(Language.BORDER_TOP.getMessage());
			player.sendMessage(Language.HELP_ADMIN.getMessage());
			player.sendMessage(Language.BORDER_BOTTOM.getMessage());
			return true;
		}
		
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////
		
		
		// Create Spleef area
		
		if(args[0].equalsIgnoreCase("create")) {
			if(args.length !=2) {
				player.sendMessage(Language.SYNTAX_CREATE.getMessage());
				return false;
			}
			if(arenas.exists(args[1])) {
				player.sendMessage(ChatColor.RED + "Error: Arena " + ChatColor.YELLOW + args[1] + ChatColor.RED + " already exists!" );
				return false;
			}
			player.sendMessage(ChatColor.GREEN + "Creating arena: " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + "...");
			arenas.createArena(player, args[1]);
			return true;
		}
		
		if(args[0].equalsIgnoreCase("delete")) {
			if(args.length !=2) {
				player.sendMessage(Language.SYNTAX_DELETE.getMessage());
				return false;
			}
			if(!arenas.exists(args[1])) {
				player.sendMessage(Language.CHAT_PREFIX.getMessage() + "" + ChatColor.RED + "Error: Arena " + ChatColor.YELLOW + args[1] + ChatColor.RED + " doesnt exist!" );
				return false;
			}
			arenas.delete(args[1]);
			player.sendMessage(Language.CHAT_PREFIX + "" + ChatColor.GREEN + "Arena " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " deleted");
			return true;
		}
		
		
		if(args[0].equalsIgnoreCase("set")) {
			if(args.length !=2) {
				player.sendMessage(Language.SYNTAX_SET.getMessage());
				return false;
			}
			
		}
		
		if(args[0].equalsIgnoreCase("reset")) {
			if(args.length !=2) {
				player.sendMessage(Language.SYNTAX_RESET.getMessage());
				return false;
			}
			
			Arena arena = arenas.getArena(args[1]);
			arena.replaceFloor();
			player.sendMessage(Language.CHAT_PREFIX.getMessage() + "" + ChatColor.YELLOW + arena.getName() + ChatColor.GREEN + "'s floor replaced");
		}
		
		
		
		
		//
		
		if(args[0].equalsIgnoreCase("join")) {
			Arena arena = arenas.getArena(args[1]);
			arena.player_join(player);
			return true;
		}
		
		if(args[0].equalsIgnoreCase("leave")) {
			//Arena arena = arenas.getArena(args[1]);
			//game.player_leave(player);
			return true;
		}
		
		if(args[0].equalsIgnoreCase("spectate") || args[0].equalsIgnoreCase("spec")) {
			Arena arena = arenas.getArena(args[1]);
			arena.player_spec(player);
			return true;
		}
		
		if(args[0].equalsIgnoreCase("debug")) {
			
			String debug_prefix = ChatColor.DARK_GRAY + "  | DEBUG |  " + ChatColor.RESET;
			String debug_msg = ChatColor.YELLOW + "";
			
			TextComponent message = new TextComponent("Click me");
			message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org"));
			message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Visit the Spigot website!")));

			
			player.spigot().sendMessage(message);
			return true;
		}
		
		if(args[0].equalsIgnoreCase("list")) {
			player.sendMessage("Arenas: " + arenas.getArenaList());
		}
		
        return false;
    }

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if(!label.equalsIgnoreCase("spleef")) return null;
        
        if(args.length == 1) {
        	for(String qCMD : CMDs) {
        		commands.add(qCMD);
        	}
            StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        
        String[] cmdsNeedArenaList = {"join", "getinfo", "set", "spec", "spectate", "reset", "delete"};
        List<String> test = Arrays.asList(cmdsNeedArenaList);
        
        if(args.length == 2) {
        	if(test.contains(args[0])) {
            	arenas.getArenaList().forEach((n) -> commands.add(n));
                StringUtil.copyPartialMatches(args[1], commands, completions);
            }
        }
        
        Collections.sort(completions);
        return completions;
	}
}


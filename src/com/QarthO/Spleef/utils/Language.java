package com.QarthO.Spleef.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;


public enum Language {
		
	//Chat Prefix
	CHAT_PREFIX(ChatColor.AQUA + "[" + ChatColor.GRAY + "%n" + ChatColor.AQUA + "] " + ChatColor.RESET),
	
	//Chat UI Border
	BORDER_TOP(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "   [" + ChatColor.GRAY + " %n v%v" + ChatColor.AQUA + " " + ChatColor.STRIKETHROUGH + "]                    "),
	BORDER_BOTTOM(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "                                          "),
	
	//Help
	HELP(ChatColor.YELLOW + "/spleef\n"
			+ "/%c join\n"
			+ "/%c leave\n"
			+ "/%c spectate"),
	
	HELP_MOD(ChatColor.YELLOW + "/%c force start\n"
			+ "/%c force end"),
	
	HELP_ADMIN(ChatColor.YELLOW + "/%c force start\n"
			+ "%c force end\n"
			+ "/%c create\n"
			+ "/%c set spectate\n"
			+ "/%c set start"),
	
	
	
	// Syntax
	SYNTAX_CREATE(ChatColor.RED + "Syntax: /spleef create <arena_name>"),
	SYNTAX_DELETE(ChatColor.RED + "Syntax: /spleef delete <arena_name>"),
	SYNTAX_SET(ChatColor.RED + "Syntax: /spleef set <join|spec|zone>"),
	
	
	
	
	//Errors - In game
	ERROR_CONSOLE_MESSAGE(ChatColor.RED + "Error: In game command only"),
	ERROR_INCORRECT_USAGE(ChatColor.RED + "Error: Incorrect usage"),
	ERROR_NO_PERMISSIONS(ChatColor.RED + "Error: Insuffictient permissions"),
	ERROR_PLAYER_NOT_FOUND(ChatColor.RED + "Error: Player not found"),
	ERROR_UKNOWN_COMMAND(ChatColor.RED + "Not a valid command\nTry /%c help"),
	
	
	
	//Succes/Errors - Plugin
	ARENA_YML(ChatColor.GREEN + "Generated 'qArena.yml'"),
	ERROR_FAILED_ARENA_YML_CREATE(ChatColor.RED + "Failed creating 'qArenas.yml'. Reload server, if problem persists contact qDeveloper"),
	ERROR_FAILED_ARENA_YML_SAVE(ChatColor.RED + "Failed saving 'qArenas.yml'"),
	
	LOCATION("(x, y, z)");
	
	String Message;
	
	private String version = "1.0";
	private String project_name = "qSpleef";
	private String author = "QarthO";
	private String cmd = "spleef";
	
	private Language(String message) {
		
		message = message.replaceAll("%a", author);
		message = message.replaceAll("%v", version);
		message = message.replaceAll("%n", project_name);
		message = message.replaceAll("%c", cmd);

		this.Message = message;		
	}
	
	public String getMessage() {
		return Message;
	}
	
	public String loc(Location loc) {
		Message = Message.replaceAll("x", "" + loc.getX());
		Message = Message.replaceAll("y", "" + loc.getY());
		Message = Message.replaceAll("z", "" + loc.getZ());

		return Message;
	}

}

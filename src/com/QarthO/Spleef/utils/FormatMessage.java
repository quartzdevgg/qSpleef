package com.QarthO.Spleef.utils;

import org.bukkit.Location;

import net.md_5.bungee.api.ChatColor;

public class FormatMessage {
	
	public static String loc(Location loc) {
		String str = "(x, y, z)";
		str = str.replaceAll("x", "" + loc.getX());
		str = str.replaceAll("y", "" + loc.getY());
		str = str.replaceAll("z", "" + loc.getZ());
		return str;
	}
	
	public static String syntax(String syntax) {
		return Language.CHAT_PREFIX.getMessage() + ChatColor.RED + "Syntax: " + syntax;
	}
	
	public static String error(String error) {
		return Language.CHAT_PREFIX.getMessage() + ChatColor.RED + "Error: " + error;
	}
	
}

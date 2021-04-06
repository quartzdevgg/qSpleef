package com.QarthO.Spleef.Commands;

import com.QarthO.Spleef.CommandsManager;

abstract public class Command {
	
	String permission;
	String name;
	String[] aliases;
	
	public Command(CommandsManager cm) {
	}
	
	abstract String getResponse();
	
}

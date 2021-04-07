package com.QarthO.Spleef.utils;

import java.util.Set;

import org.bukkit.plugin.PluginDescriptionFile;

import com.QarthO.Spleef.Main;

public class PluginYML {

	private PluginDescriptionFile pluginYML = Main.getPlugin(Main.class).getDescription();
	
	String projectName = pluginYML.getName();
	Set<String> commandLabels = pluginYML.getCommands().keySet();
	String version = pluginYML.getVersion();
	String author = pluginYML.getAuthors().toString();
	
	public String getProjectName() {
		return projectName;
	}
	
	public Set<String> getCommandLabels() {
		return commandLabels;
	}
	
	public String getPluginVersion() {
		return version;
	}
	
	public String getPluginAuthor() {
		return author;
	}
}

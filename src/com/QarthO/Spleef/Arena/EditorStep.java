package com.QarthO.Spleef.Arena;

import com.QarthO.Spleef.utils.Language;
import org.bukkit.ChatColor;

public enum EditorStep {
	COMPLETED(null, "", "%prefix" + ChatColor.GREEN + "Arena " + ChatColor.YELLOW + "%arena" + ChatColor.GREEN + " %f"),
	SPEC(COMPLETED, "%prefix" +ChatColor.YELLOW + "Select spectate warp", "%prefix" + ChatColor.LIGHT_PURPLE + "Spectate Selected: %loc"),
	JOIN(SPEC, "%prefix" + ChatColor.YELLOW +"Select join warp", "%prefix" + ChatColor.LIGHT_PURPLE + "Join Selected: %loc"),
	LOC2(JOIN, "%prefix" + ChatColor.YELLOW +"Select corner 2", "%prefix" + ChatColor.LIGHT_PURPLE + "Corner 2 Selected: %loc"),
	LOC1(LOC2, "%prefix" + ChatColor.YELLOW + "Select corner 1", "%prefix" + ChatColor.LIGHT_PURPLE + "Corner 1 Selected: %loc");
	
	EditorStep next;
	String msgStart;
	String msgFinish;
	
	private EditorStep(EditorStep next, String msgStart, String msgFinish) {
		this.next = next;
		
		msgStart = msgStart.replaceFirst("%prefix", Language.CHAT_PREFIX.getMessage());
		msgFinish = msgFinish.replaceFirst("%prefix", Language.CHAT_PREFIX.getMessage());
		
		this.msgStart = msgStart;
		this.msgFinish = msgFinish;
	}
	
	public String getMessageStart() {
		return msgStart;
	}
	
	public String getMessageFinish() {
		return msgFinish;
	}
	
	public EditorStep nextStep() {
		return next;	
	}
}

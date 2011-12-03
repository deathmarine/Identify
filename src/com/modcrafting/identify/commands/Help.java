package com.modcrafting.identify.commands;

import org.bukkit.entity.Player;

import com.modcrafting.identify.Identify;

public class Help {
	static Identify plugin;
	public Help(Identify identify) {
		Help.plugin = identify;
	}
	public boolean showHelp(Player sender) {
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		
		// TODO Auto-generated method stub
		return true;
	}
		
}
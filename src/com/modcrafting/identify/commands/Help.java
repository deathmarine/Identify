package com.modcrafting.identify.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.modcrafting.identify.Identify;

public class Help {
	static Identify plugin;
	public Help(Identify identify) {
		Help.plugin = identify;
	}
	public boolean showHelp(Player sender) {
		sender.sendMessage(ChatColor.DARK_AQUA + "Identify Help");
		sender.sendMessage(ChatColor.DARK_AQUA + "-----------------------------------------");
		sender.sendMessage(ChatColor.DARK_AQUA + "/identify");
		sender.sendMessage(ChatColor.DARK_AQUA + "If config is set for random: true");
		sender.sendMessage(ChatColor.DARK_AQUA + "use /identify buy - random enchantment.");
		sender.sendMessage(ChatColor.DARK_AQUA + "/identify list");
		sender.sendMessage(ChatColor.DARK_AQUA + "/identify set");
		sender.sendMessage(ChatColor.DARK_AQUA + "/identify reload");
		sender.sendMessage(ChatColor.DARK_AQUA + "/identify version");
		return true;
	}
		
}
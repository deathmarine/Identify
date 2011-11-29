package com.modcrafting.identify.commands;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.modcrafting.identify.Identify;
import com.nijikokun.bukkit.Permissions.Permissions;

public class IdentifyCommand implements CommandExecutor {
	public static final Logger log = Logger.getLogger("Minecraft");
	Identify plugin;

	public IdentifyCommand(Identify identify) {

		this.plugin = identify;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
    	YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
		boolean auth = false;
		boolean server = false;
		Player player = null;
		String admin = "server";
		int randprice = config.getInt("randomprice");
		if (sender instanceof Player){
			player = (Player)sender;
			if (Permissions.Security.permission(player, "identify.item")){
				auth = true;
			}else{
			 if (player.isOp()) auth = true; //defaulting to Op if no permissions or node
			}
			admin = player.getName();
		}else{
			auth = true;
			server = true;//if sender is not a player - Console
		}
		if (!auth){
			sender.sendMessage(ChatColor.RED + "You do not have the required permissions.");
			return true;
		}
		if (args.length < 1){
			ItemStack item = player.getItemInHand();
			if (server){
				sender.sendMessage("Your Not Able To Identify.");
			}
			if (item == null){
				sender.sendMessage("Your Not Holding Anything.");
				return true;
			}
			
			if(plugin.random){
				if(args[0].equalsIgnoreCase("buy")){
					int price = config.getInt("randomprice");
					
					return true;
				}
			}
			if(args[0].equalsIgnoreCase("list")){
				//list available enchantments for item in hand
				return true;
			}
			if(args[0].equalsIgnoreCase("buy")){
				int price = config.getInt("priceperlevel");
				return true;
			}
			if(args[0].equalsIgnoreCase("")){
				return true;
			}
			//if () return true;
			
			return true;
		}
		if (auth){
			if (plugin.random){
				sender.sendMessage("Identify your item for " + randprice);
			}
		}
		return false;
	}
}

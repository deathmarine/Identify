package com.modcrafting.identify.commands;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import com.modcrafting.identify.Identify;
/*
 * 
 */
public class IdentifyCommand implements CommandExecutor {
	public static final Logger log = Logger.getLogger("Minecraft");
	Identify plugin;

	public IdentifyCommand(Identify identify) {

		this.plugin = identify;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
    	YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
		boolean auth = true; //fortes
		boolean server = false;
		Player player = null;
		if (sender instanceof Player){
			player = (Player)sender;
		}else{
			auth = true;
			server = true;//if sender is not a player - Console
		}
		if (!auth){
			sender.sendMessage(ChatColor.RED + "You do not have the required permissions.");
			return true;
		}
		//Check and display differences in config
		if (args.length < 1){
			if (auth){
				if (plugin.random){
					int randprice = config.getInt("prices.randomprice");
					sender.sendMessage("Identify your item for " + Integer.toString(randprice));
					sender.sendMessage("Use /identify buy.");
					return true;
				}else{
					return false;
				}
			}
		}	
		if(server){
			sender.sendMessage("Your Not Able To Identify.");
			return true;
		}	
		if(args[0].equalsIgnoreCase("list")){
			if(plugin.random) return false;
			List.showList(player);
			return true;
		}
		if(args[0].equalsIgnoreCase("buy")){
			if(Buying.buyRandom(player))return true;
			return false;
		}
		if(args[0].equalsIgnoreCase("help")){
			if(Help.showHelp(player))return true;
			return false;
		}
		return false;
	}
}

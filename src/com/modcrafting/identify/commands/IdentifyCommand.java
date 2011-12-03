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
		boolean auth = true;
		boolean adminAuth = false;
		boolean server = false;
		Player player = null;
		
		if (sender instanceof Player){
			player = (Player)sender;
			if (plugin.setupPermissions()){
				if (plugin.permission.has(player, "identify.use")) auth = true;
				if (plugin.permission.has(player, "identify.admin")) adminAuth = true;
			}else{
				if (player.isOp()){
				 auth = true;
				 adminAuth = true;//defaulting to Op if no vault doesn't take or node
				}
			 }
		}else{
			auth = true;
			adminAuth = true;//if sender is not a player - Console
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
					sender.sendMessage(ChatColor.DARK_AQUA + "Stay a while and Listen");
					sender.sendMessage(ChatColor.DARK_AQUA + "Identify your item for " + ChatColor.BLUE + Integer.toString(randprice));
					sender.sendMessage(ChatColor.DARK_AQUA + "Use /identify buy.");
					return true;
				}else{
					//display help for non random items.
					return true;
				}
			}
		}
		
		if(server){
			sender.sendMessage("Your Not Able To Identify.");
			return true;
		}
		/*
		if(args[0].equalsIgnoreCase("list")){
			if(plugin.random) return false;
			plugin.list.showList(player);
			return true;
		}
		*/
		if(args[0].equalsIgnoreCase("buy")){
			if (plugin.setupPermissions()){
				if(auth){
					if(plugin.buy.buyRandom(player))return true;
				}
			}	
				return false;
		}
		
		if(args[0].equalsIgnoreCase("help")){
			if(plugin.help.showHelp(player))return true;
			return false;
		}
		
		if(args[0].equalsIgnoreCase("set")){
			if (args.length < 2) sender.sendMessage("/identify set <levelprice/randomprice> <price>");
				if (adminAuth){
					plugin.set.set(args[1], args[2], args[3], player);
				}else{
					sender.sendMessage(ChatColor.RED + "You do not have the required permissions.");
				}
			return true;
		}
		
		return false;
	}
}

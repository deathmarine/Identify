package com.modcrafting.identify.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

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
    	boolean auth = true;
		boolean adminAuth = false;
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
					YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
					int randprice = config.getInt("prices.randomprice");
					sender.sendMessage(ChatColor.DARK_AQUA + "Stay a while and Listen");
					sender.sendMessage(ChatColor.DARK_AQUA + "Identify your item for " + ChatColor.BLUE + Integer.toString(randprice));
					sender.sendMessage(ChatColor.DARK_AQUA + "Use /identify buy.");
					return true;
				}else{
					YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
					int randprice = config.getInt("prices.levelprice");
					sender.sendMessage(ChatColor.DARK_AQUA + "Stay a while and Listen");
					sender.sendMessage(ChatColor.DARK_AQUA + "Enchant your item for " + ChatColor.BLUE + Integer.toString(randprice));
					sender.sendMessage(ChatColor.DARK_AQUA + "Use /identify list.");
					return true;
				}
			}
		}
		
		if(args[0].equalsIgnoreCase("list")){
			if(plugin.random) return false;

			if(args.length < 2){
				plugin.list.showList(player);
				return true;
			}
			if(args[1].equalsIgnoreCase("tools")){
				plugin.list.toolList(player);
				return true;
			}
			if(args[1].equalsIgnoreCase("armor")){
				plugin.list.armorList(player);
				return true;
			}
			if(args[1].equalsIgnoreCase("weapons")){
				plugin.list.weaponList(player);
				return true;
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("buy")){
			YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
				if(auth){
					if(config.getBoolean("random", true)){
						if(plugin.buy.buyRandom(player))return true;
					}else{
						if(args.length < 2) return false;
						if(args[1].equalsIgnoreCase("random")){
							if(plugin.buy.buyRandom(player))return true;							
						}
						if(plugin.buy.buyList(player, args[1])) return true;
						return false;
					}
				}
				
				return false;
		}
		
		if(args[0].equalsIgnoreCase("help")){
			if(plugin.help.showHelp(player))return true;
			return false;
		}

		if(args[0].equalsIgnoreCase("reload")){
			if(adminAuth){
				log.log(Level.SEVERE, "[Identify] disabling.");
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				log.log(Level.SEVERE, "[Identify] attempting restart.");
				plugin.getServer().getPluginManager().enablePlugin(plugin);
				return true;
			}
		}

		if(args[0].equalsIgnoreCase("version")){
			PluginDescriptionFile pdfFile = plugin.getDescription();
			sender.sendMessage("[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " is enabled!");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("set")){
				if (args.length < 2){
					sender.sendMessage(ChatColor.GRAY + "/Identify Set <levelprice/randomprice/random>");
					return true;
				}
				if (adminAuth){
						if(args[1].equalsIgnoreCase("levelprice")){
							if (args.length < 3){
								sender.sendMessage("/Identify Set levelprice <amount>");
								return true;
							}
							YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
							String p = args[2];
							int pvar = Integer.parseInt(p);
							config.set("prices.levelprice", (int) pvar);
							plugin.saveConfig();
							sender.sendMessage("Level Enchant Price Set to " + p + "!");
							Identify.log.log(Level.INFO, "[Identify] Level Enchant Price Set to " + p + "!");
							plugin.getServer().getPluginManager().disablePlugin(plugin);
							plugin.getServer().getPluginManager().enablePlugin(plugin);
							return true;
						}
						if(args[1].equalsIgnoreCase("randomprice")){
							if (args.length < 3){
								sender.sendMessage("/Identify Set randomprice <amount>");
								return true;
							}
							YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
							String p = args[2];
							int pvar = Integer.parseInt(p);
							config.set("prices.randomprice", (int) pvar);
							plugin.saveConfig();
							sender.sendMessage("iRandom Enchant Price Set to " + p + "!");
							Identify.log.log(Level.INFO, "[Identify] Random Enchant Price Set to " + p + "!");
							plugin.getServer().getPluginManager().disablePlugin(plugin);
							plugin.getServer().getPluginManager().enablePlugin(plugin);
							return true;
						}
						if(args[1].equalsIgnoreCase("random")){
							if (args.length < 3){
								sender.sendMessage("/Identify Set random <true/false>");
								return true;
							}
							boolean pvar = args[2].equalsIgnoreCase("true");
							YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
							config.set("random", (boolean) pvar);
							plugin.saveConfig();
							sender.sendMessage("Random Enchant Set to " + args[2] + "!");
							Identify.log.log(Level.INFO, "[Identify] Random Enchant Set to " + args[2] + "!");
							plugin.getServer().getPluginManager().disablePlugin(plugin);
							plugin.getServer().getPluginManager().enablePlugin(plugin);
							return true;
						}
					return true;
				}else{
					sender.sendMessage(ChatColor.RED + "You do not have the required permissions.");
					return true;
				}
		}
		
		return false;
	}
}

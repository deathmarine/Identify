package com.modcrafting.identify.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;

import com.modcrafting.identify.Identify;
/*
 * 
 */
public class IdentifyCommand implements CommandExecutor {
	Identify plugin;
	public IdentifyCommand(Identify identify) {
		this.plugin = identify;
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (!sender.hasPermission("")){
			sender.sendMessage(ChatColor.RED + "You do not have the required permissions.");
			return true;
		}
		Player player = null;
		if (sender instanceof Player){
			player = (Player)sender;
		}
		if(args[0].equalsIgnoreCase("list")){

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
			if(args[1].equalsIgnoreCase("bow")){
				plugin.list.arrowList(player);
				return true;
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("buy")){
			double bal = plugin.economy.getBalance(sender.getName());
			if(args.length<2){
				sender.sendMessage(ChatColor.GRAY+" /identify buy <dd|tier|name|enchant|lore|random>");
				return true;
			}
			if(args[1].equalsIgnoreCase("diablodrop")||args[1].equalsIgnoreCase("dd")){
				double price = plugin.ddConfig.getDouble("diablodrop.price", 1000);
				if(price > bal){
					sender.sendMessage(ChatColor.DARK_AQUA + "Your don't have enough money!");
					return true;
				}else{
					CraftItemStack tool = plugin.getDiabloDrops().dropsAPI.getItem();
					while(tool==null){
						tool = plugin.getDiabloDrops().dropsAPI.getItem();
					}
					com.modcrafting.toolapi.lib.Tool t = new com.modcrafting.toolapi.lib.Tool(tool);
					String name = t.getName();
					player.getInventory().addItem(t);
					player.updateInventory();
					plugin.economy.withdrawPlayer(sender.getName(), Math.abs(price));
					sender.sendMessage(ChatColor.DARK_AQUA + "You were charged: " + ChatColor.BLUE + String.valueOf(price));
					sender.sendMessage(ChatColor.DARK_AQUA + "For: " + ChatColor.BLUE + name);
					return true;
				}
			}
			if(args[1].equalsIgnoreCase("tier")){
				for(com.modcrafting.diablodrops.tier.Tier tier:plugin.getDiabloDrops().tiers){
					if(args[2].equalsIgnoreCase(tier.getName())){
						double price = plugin.ddConfig.getDouble(tier.getName()+".price", 1000);
						if(price < bal){
							sender.sendMessage(ChatColor.DARK_AQUA + "Your don't have enough money!");
							return true;
						}
						CraftItemStack tool = plugin.getDiabloDrops().dropsAPI.getItem(tier);
						while(tool==null){
							tool = plugin.getDiabloDrops().dropsAPI.getItem(tier);
						}
						com.modcrafting.toolapi.lib.Tool t = new com.modcrafting.toolapi.lib.Tool(tool);
						String name = t.getName();
						player.getInventory().addItem(t);
						player.updateInventory();
						plugin.economy.withdrawPlayer(sender.getName(), Math.abs(price));
						sender.sendMessage(ChatColor.DARK_AQUA + "You were charged: " + ChatColor.BLUE + String.valueOf(price));
						sender.sendMessage(ChatColor.DARK_AQUA + "For: " + ChatColor.BLUE + name);
						return true;								
					}						
				}
				sender.sendMessage(ChatColor.GOLD+"[DiabloDrops]"
						+ChatColor.GRAY+" Tier: "+args[2]+" not found.");
				return true;
			}
			if(args[1].equalsIgnoreCase("name")){
				double price = 1000;
				String name = new String();
				ItemStack item = player.getItemInHand();
				if (item==null||item.getType() == Material.AIR){
					sender.sendMessage(ChatColor.DARK_AQUA + "Your Not Holding Anything.");
					return true;
				}
				if(plugin.ddConfig.getBoolean("diablodrop.name.flat",true)){
					price = plugin.ddConfig.getDouble("diablodrop.name.flatrate", 1000);
					if(price < bal){
						sender.sendMessage(ChatColor.DARK_AQUA + "Your don't have enough money!");
						return true;
					}
					plugin.economy.withdrawPlayer(sender.getName(), Math.abs(price));
				}else{
					price = plugin.ddConfig.getDouble("diablodrop.name.priceper", 10);
					price = price*name.length();
					if(price < bal){
						sender.sendMessage(ChatColor.DARK_AQUA + "Your don't have enough money!");
						return true;
					}
					plugin.economy.withdrawPlayer(sender.getName(), Math.abs(price));
				}
				com.modcrafting.toolapi.lib.Tool t = new com.modcrafting.toolapi.lib.Tool(item);
				t.setName(name);
				sender.sendMessage(ChatColor.GOLD+"[DiabloDrops]"
						+ChatColor.GRAY+" Name: "+name+" was set for "+ChatColor.GOLD+String.valueOf(price));
				return true;
			}
			if(args[1].equalsIgnoreCase("lore")){
				//TODO: Working my way down.
			}
			if(args[1].equalsIgnoreCase("random")){
				plugin.buy.buyRandom(player);
				return true;						
			}
			if(args[1].equalsIgnoreCase("enchant")&&args.length>2){
				plugin.buy.buyList(player, args); 
				return true;
			}
		}
		
		if(args[0].equalsIgnoreCase("help")){
			if(plugin.help.showHelp(player))return true;
			return false;
		}

		if(args[0].equalsIgnoreCase("reload")){
			if (sender.hasPermission("")){
				plugin.getLogger().info("disabling.");
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				plugin.getLogger().info("attempting restart.");
				plugin.getServer().getPluginManager().enablePlugin(plugin);
				return true;
			}
		}
		if(args[0].equalsIgnoreCase("set")){
				if (args.length < 2){
					sender.sendMessage(ChatColor.GRAY + "/Identify Set <levelprice/randomprice/random>");
					return true;
				}
				if (sender.hasPermission("")){
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
							plugin.getLogger().info("Level Enchant Price Set to " + p + "!");
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
							plugin.getLogger().info("Random Enchant Price Set to " + p + "!");
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
							plugin.getLogger().info("Random Enchant Set to " + args[2] + "!");
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

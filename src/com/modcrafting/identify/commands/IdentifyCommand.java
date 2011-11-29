package com.modcrafting.identify.commands;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.modcrafting.identify.Identify;
//import com.nijikokun.bukkit.Permissions.Permissions;
/*
 * 
 * EPIC IF STATEMENTS BATMAN
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class IdentifyCommand implements CommandExecutor {
	public static final Logger log = Logger.getLogger("Minecraft");
	Random generator = new Random();
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
		int randprice = config.getInt("prices.randomprice");
		if (sender instanceof Player){
			player = (Player)sender;
			/*
			 * if (Permissions.Security.permission(player, "identify.item")){
			 
				auth = true;
			}else{
			 if (player.isOp()) auth = true; //defaulting to Op if no permissions or node
			}
			*Reconstruct Permissions
			*/
			//TODO Add permission node
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
				return true;
			}
			if (item == null){
				sender.sendMessage("Your Not Holding Anything.");
				return true;
			}
			
			
			if(plugin.random){
				if(args[0].equalsIgnoreCase("buy")){
					int iprice = config.getInt("prices.randomprice");
					double price = Double.parseDouble(Integer.toString(iprice));
					int roll = generator.nextInt(35) + 1;
					int randomizer = roll;
					String eitemPrice = Integer.toString(iprice);
					plugin.economy.withdrawPlayer(sender.getName(), price);
					Enchantment eItem = new EnchantmentWrapper(randomizer);
					int power = generator.nextInt(eItem.getMaxLevel()) + 1;
					//boolean validEnchant = Enchantment.isAcceptingRegistrations();
					boolean checkItem = eItem.canEnchantItem(item);
					sender.sendMessage("You were charged " + eitemPrice + " to identify your item!");
					
					if(eItem != null){
						if (!checkItem){
							sender.sendMessage("Not Able To Identify That, Sorry.");
							return true;
						}else{
						boolean eCheck = item.containsEnchantment(eItem);
						if (eCheck){
							item.addUnsafeEnchantment(eItem, power);
							return true;
						}else{
						String itemName = item.getType().toString();
						String eitemName = eItem.getName();
						String eitemPower = Integer.toString(power);
						sender.sendMessage("You've identified your " + itemName + " as :" + eitemName + "!");
						sender.sendMessage("Power Level: " + eitemPower);
						item.addUnsafeEnchantment(eItem, power);
						return true;
						}
						}
					}
					
				}
			}
			if(args[0].equalsIgnoreCase("list")){
				if(plugin.random) return false;
				//list Enchants and Levels plus prices.
				sender.sendMessage("");
				return true;
			}
			if(args[0].equalsIgnoreCase("buy")){
				int iprice = config.getInt("prices.levelprice");
				double price = Double.parseDouble(Integer.toString(iprice));
				int roll = generator.nextInt(35) + 1;
				int randomizer = roll;
				Enchantment eItem = new EnchantmentWrapper(randomizer);
				int power = generator.nextInt(eItem.getMaxLevel()) + 1;
				//boolean validEnchant = Enchantment.isAcceptingRegistrations();
				boolean checkItem = eItem.canEnchantItem(item);
					
				if(eItem != null){
					if (!checkItem){
						sender.sendMessage("Not Able To Identify That.");
						return true;
					}else{
					boolean eCheck = item.containsEnchantment(eItem);
						if (eCheck){
							item.addUnsafeEnchantment(eItem, power);
							return true;
						}else{
							String itemName = item.getType().toString();
							String eitemName = eItem.getName();
							String eitemPower = Integer.toString(power);
							String eitemPrice = Integer.toString(iprice);
							sender.sendMessage("You were charged " + eitemPrice + " to identify your item!");
							sender.sendMessage("You've identified your " + itemName + " as :" + eitemName + "!");
							sender.sendMessage("Power Level: " + eitemPower);
							plugin.economy.withdrawPlayer(sender.getName(), price);
							item.addUnsafeEnchantment(eItem, power);
							return true;
						}
					}
				}
					
			}
			if(args[0].equalsIgnoreCase("help")){
				//TODO Build help system
				return true;
			}
			/*Build Configuration Commands Completed
			 * Set
			 * levelprice-done
			 * randomprice-done
			 * type-done
			 * 
			 */
			if(args[0].equalsIgnoreCase("set")){
				if(args[1].equalsIgnoreCase("levelprice")){
					String p = args[2];
					if (p == null){
						sender.sendMessage("You must specify a value.");
						return true;
					}
					int pvar = Integer.parseInt(p);
					config.set("prices.levelprice", (int) pvar);
					plugin.saveConfig();
					sender.sendMessage("Level Enchant Price Set to " + p + "!");
					Identify.log.log(Level.INFO, "[Identify] Level Enchant Price Set to " + p + "!");
					return true;
				}
				if(args[2].equalsIgnoreCase("randomprice")){
					String p = args[2];
					if (p == null){
						sender.sendMessage("You must specify a value.");
						return true;
					}
					int pvar = Integer.parseInt(p);
					config.set("prices.randomprice", (int) pvar);
					plugin.saveConfig();
					sender.sendMessage("Random Enchant Price Set to " + p + "!");
					Identify.log.log(Level.INFO, "[Identify] Random Enchant Price Set to " + p + "!");
					return true;
				}
				if(args[1].equalsIgnoreCase("type")){
					if(args[2].equalsIgnoreCase("tools")){
						if(args.length < 3){
							sender.sendMessage("Select True or False!");
							return true;
						}
						boolean tool = new Boolean(args[3]);
						if(tool){
							config.set("enchantTools", (boolean) true);
							sender.sendMessage("Enchant Tools Set True!");
							Identify.log.log(Level.INFO, "[Identify] Enchant Tools Set to True!");
						}else{
							config.set("enchantTools", (boolean) false);
							sender.sendMessage("Enchant Tools Set False!");
							Identify.log.log(Level.INFO, "[Identify] Enchant Tools Set to False!");
						}
						plugin.saveConfig();
						return true;
					}
					if(args[2].equalsIgnoreCase("armor")){
						if(args.length < 3){
							sender.sendMessage("Select True or False!");
							return true;
						}
						boolean armor = new Boolean(args[3]);
						if(armor){
							config.set("enchantArmor", (boolean) true);
							sender.sendMessage("Enchant Armor Set True!");
							Identify.log.log(Level.INFO, "[Identify] Enchant Armor Set to True!");
						}else{
							config.set("enchantArmor", (boolean) false);
							sender.sendMessage("Enchant Armor Set False!");
							Identify.log.log(Level.INFO, "[Identify] Enchant Armor Set to False!");
						}
						plugin.saveConfig();
						return true;
						
					}
					sender.sendMessage("/identify set type <tools/armor>");
					return true;
				}
				sender.sendMessage("/identify set <levelprice/randomprice/type>");
				return true;
			}
			/*
			 * End of Set
			 */
			return true;
		}
		if (auth){
			if (plugin.random){
				String itemvar = toString();
				sender.sendMessage("Identify " + itemvar + " for " + randprice);
				return true;
			}
		}
		return false;
	}
}

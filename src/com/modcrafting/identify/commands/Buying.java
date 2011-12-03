package com.modcrafting.identify.commands;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.modcrafting.identify.Identify;

public class Buying {
	Identify plugin;
	public Buying(Identify identify) {
		this.plugin = identify;
	}
	/*
	 * This Method should work.
	 * Use inline with IdentifyCommand
	 * 
	 */
	public boolean buy(Player sender){
		YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
		boolean rand = config.getBoolean("random", true);
		if(rand){
			if(buyRandom(sender)) return true;
		}else{
			if(buyList(sender)) return true;
		}
		return false;
	}
	public boolean buyList(Player sender){
		return false;
	}
	
	
	
	public boolean buyRandom(Player sender){
		//Config
		YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
		int iprice = config.getInt("prices.randomprice", 1000);
		String eitemPrice = Integer.toString(iprice);
		
		//Random
		Random generator = new Random();
		//Item
		ItemStack item = sender.getItemInHand();
		if (item.getType() == Material.AIR){
			sender.sendMessage("Your Not Holding Anything.");
			return true;
		}
		String itemName = item.getType().toString();
				int randomizer = generator.nextInt(17) + 1;
				Enchantment eItem = Enchant.enchant(randomizer);
				Enchantment eItem1 = null;
				Enchantment eItem2 = null;
				Enchantment eItem3 = null;
				Enchantment eItem4 = null;
				int power = generator.nextInt(eItem.getMaxLevel()) + 1;
				int power1 = 0;
				int power2 = 0;
				int power3 = 0;
				int power4 = 0;
				
				//Random Levels
				//Max Amount of random enchants 5
				int randomMax = config.getInt("randomMax", 5);
				int randMax = generator.nextInt(randomMax) + 1;
					if (randMax > 1){
						int randomizer1 = generator.nextInt(17) + 1;
						eItem1 = Enchant.enchant(randomizer1);
						power1 = generator.nextInt(eItem1.getMaxLevel()) + 1;
						if (randMax > 2){
							int randomizer2 = generator.nextInt(17) + 1;
							eItem2 = Enchant.enchant(randomizer2);
							power2 = generator.nextInt(eItem2.getMaxLevel()) + 1;
							if (randMax > 3){
								int randomizer3 = generator.nextInt(17) + 1;
								eItem3 = Enchant.enchant(randomizer3);
								power3 = generator.nextInt(eItem3.getMaxLevel()) + 1;
								if (randMax > 4){
									int randomizer4 = generator.nextInt(17) + 1;
									eItem4 = Enchant.enchant(randomizer4);
									power4 = generator.nextInt(eItem4.getMaxLevel()) + 1;
								}
							}
						}
					}
					
					boolean testEitem = item.containsEnchantment(eItem);
							if(testEitem){
								sender.sendMessage("This item is already identified.");
								return true;
							}else{
								if(plugin.setupEconomy()){
							}
								double bal = plugin.economy.getBalance(sender.getName());
								double amtd = Double.valueOf(eitemPrice.trim());
								if(amtd > bal){
									sender.sendMessage(ChatColor.DARK_AQUA + "Your don't have enough money!");
									return true;
								}else{
									plugin.economy.withdrawPlayer(sender.getName(), amtd);
								}
								sender.sendMessage(ChatColor.DARK_AQUA + "You were charged: " + ChatColor.BLUE + eitemPrice);
								sender.sendMessage(ChatColor.DARK_AQUA + "Your " + ChatColor.BLUE + itemName + ChatColor.DARK_AQUA + " has been identified!");
								item.addUnsafeEnchantment(eItem, power); 
								if (eItem1 != null)	item.addUnsafeEnchantment(eItem1, power1);
								if (eItem2 != null)	item.addUnsafeEnchantment(eItem2, power2);
								if (eItem3 != null)	item.addUnsafeEnchantment(eItem3, power3);
								if (eItem4 != null)	item.addUnsafeEnchantment(eItem4, power4);
								return true;
							}	
				
				
				
		/*		}else{
					sender.sendMessage("This item is not identifable.");
					
				}
				*/
			//}
		//return false;
		}
	}
	

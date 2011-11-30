package com.modcrafting.identify.commands;

import java.util.Random;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.modcrafting.identify.Identify;

public class Buying {
	static Identify plugin;
	public Buying(Identify identify) {

		Buying.plugin = identify;
	}
/*
 * This Method should work.
 * Use inline with IdentifyCommand
 * 
 */
	public boolean buyList(String arg1){
		return false;
	}
	public static boolean buyRandom(Player sender){
		//Config
	    YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
		int iprice = config.getInt("prices.randomprice");
		double price = Double.parseDouble(Integer.toString(iprice));
		String eitemPrice = Integer.toString(iprice);
		
		//Random
		Random generator = new Random();
		int randomizer = generator.nextInt(17) + 1;
		
		//Item
		ItemStack item = sender.getItemInHand();
		if (item == null){
			sender.sendMessage("Your Not Holding Anything.");
			return true;
		}
		String itemName = item.getType().toString();

		if(plugin.random){
				//Buy
				plugin.economy.withdrawPlayer(sender.getName(), price);
				sender.sendMessage("You were charged: " + eitemPrice);
				//Get Enchantment
				Enchantment eItem = Enchant.enchant(randomizer);
				int power = generator.nextInt(eItem.getMaxLevel()) + 1;

				boolean testEitem = item.containsEnchantment(eItem);
						if(testEitem){
							item.addEnchantment(eItem, power);
							sender.sendMessage("This item is already identified.");
							return true;
						}else{
							String eitemName = eItem.getName();
							String eitemPower = Integer.toString(power);
							sender.sendMessage("You've identified your " + itemName + " as :" + eitemName + "!");
							sender.sendMessage("Power Level: " + eitemPower);
							item.addEnchantment(eItem, power);
							return true;
						}
							
			}
		return false;
		}
	}
	

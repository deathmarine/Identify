package com.modcrafting.identify.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.modcrafting.identify.Identify;

public class Buying {
	Random generator = new Random();
	Identify plugin;
	FileConfiguration config;
	public Buying(Identify identify) {
		this.plugin = identify;
		config = plugin.getConfig();
	}
	
	public void buyList(Player sender, String[] args){
		int iprice = config.getInt("Enchantment.Price", 1000);
		int max = config.getInt("Enchantment.Max", 10);
		ItemStack item = sender.getItemInHand();
		if (item==null||item.getType() == Material.AIR){
			sender.sendMessage("Your Not Holding Anything.");
			return;
		}
		if (args[2].equalsIgnoreCase("all")){
			buyAll(sender);
		}
		Enchantment enchant = Enchantment.getByName(args[2].toUpperCase());
		if(enchant == null){
			int pvar = 0;
			try{
				pvar = Integer.parseInt(args[2]);
			}catch(NumberFormatException nfe){
				sender.sendMessage(ChatColor.GRAY+"Identify can find enchantment: "+args[2]);
				return;
			}
			enchant = Enchantment.values()[pvar];
		}
		String enchName = enchant.getName();
		int lvl = 1;
		try{
			lvl = Integer.parseInt(args[3]);
		}catch(NumberFormatException nfe){
			sender.sendMessage(ChatColor.GRAY+"Identify can't set the enchantment lvl to: "+args[3]);
			return;
		}
		int power = item.getEnchantmentLevel(enchant) + lvl;
		if (power > max){
			sender.sendMessage(ChatColor.DARK_AQUA + "The enchantment on this item is Maxed");
			return;
		}
		String powerLvl = Integer.toString(power);

		if(!enchant.canEnchantItem(item)
				&&config.getBoolean("Enchantment.SafeEnchant",true)){
			sender.sendMessage(ChatColor.DARK_AQUA + "Your can't set this enchantment!");
			return;
		}
		int price = lvl * iprice;
		String eitemPrice = Integer.toString(price);
		double bal = plugin.economy.getBalance(sender.getName());
		double amtd = Double.parseDouble(eitemPrice);
		if(amtd > bal){
			sender.sendMessage(ChatColor.DARK_AQUA + "Your don't have enough money!");
			return;
		}else{
			plugin.economy.withdrawPlayer(sender.getName(), Math.abs(amtd));
		}
		String itemName = item.getType().toString();
		sender.sendMessage(ChatColor.DARK_AQUA + "You were charged: " + ChatColor.BLUE + eitemPrice);
		sender.sendMessage(ChatColor.DARK_AQUA + "Current Item: " + ChatColor.BLUE + itemName);
		sender.sendMessage(ChatColor.DARK_AQUA + "Enchantment " + ChatColor.BLUE + enchName + ChatColor.DARK_AQUA + " added to level " +ChatColor.BLUE + powerLvl + ChatColor.DARK_AQUA + " !");
		item.addUnsafeEnchantment(enchant, power); 
		return;
	}
	
	
	public void buyAll(Player sender){
		int lvl = config.getInt("Enchantment.Max", 10);
		double price = config.getDouble("Enchantment.Price", 1000)*21*lvl;
		ItemStack item = sender.getItemInHand();
		if (item.getType() == Material.AIR){
			sender.sendMessage(ChatColor.RED+"Your Not Holding Anything.");
			return;
		}
		if(item.getEnchantments().size()>0){
			sender.sendMessage(ChatColor.RED+"This item is already identified.");
			return;
		}else{
			double bal = plugin.economy.getBalance(sender.getName());
			if(price > bal){
				sender.sendMessage(ChatColor.DARK_AQUA + "Your don't have enough money!");
				return;
			}
		}
		String itemName = item.getType().name();
		Map<Enchantment, Integer> map = new HashMap<Enchantment,Integer>();
		for(Enchantment eItem:Enchantment.values()){
			if(eItem!=null){
				if (config.getBoolean("Enchantment.SafeEnchant",true)){
					if(eItem.canEnchantItem(item))map.put(eItem,lvl);
				}else{
					map.put(eItem,lvl);
				}
			}
		}
		item.addUnsafeEnchantments(map);
		plugin.economy.withdrawPlayer(sender.getName(), Math.abs(price));
		sender.sendMessage(ChatColor.DARK_AQUA + "You were charged: " + ChatColor.BLUE + Double.toString(price));
		sender.sendMessage(ChatColor.DARK_AQUA + "Your " + ChatColor.BLUE + itemName + ChatColor.DARK_AQUA + " has been identified!");
		return;
	}
	
	public void buyRandom(Player sender){
		double price = config.getDouble("Random.Price", 1000);
		ItemStack item = sender.getItemInHand();
		if (item.getType() == Material.AIR){
			sender.sendMessage(ChatColor.RED+"Your Not Holding Anything.");
			return;
		}
		if(item.getEnchantments().size()>0){
			sender.sendMessage(ChatColor.RED+"This item is already identified.");
			return;
		}else{
			double bal = plugin.economy.getBalance(sender.getName());
			if(price > bal){
				sender.sendMessage(ChatColor.DARK_AQUA + "Your don't have enough money!");
				return;
			}
		}
		String itemName = item.getType().name();
		int l = config.getInt("Random.Enchantments.Max", 5);
		int t=0;
		while(item.getEnchantments().size()==0&&t<3){
			for(;l>0;l--){
				Enchantment eItem = Enchantment.values()[generator.nextInt(Enchantment.values().length)];
				if(eItem!=null){
					if (config.getBoolean("SafeEnchant.Enabled",true)){
						try{
							item.addEnchantment(eItem,generator.nextInt(eItem.getMaxLevel()) + 1);
						}catch (Exception e1){
						}
					}else{
						item.addUnsafeEnchantment(eItem,generator.nextInt(eItem.getMaxLevel()) + 1);
					}
				}
			}
			t++;
		}
		if(item.getEnchantments().size()==0){
			sender.sendMessage(ChatColor.DARK_AQUA + "Your can't enchant this item!");
			return;
		}
		plugin.economy.withdrawPlayer(sender.getName(), Math.abs(price));
		sender.sendMessage(ChatColor.DARK_AQUA + "You were charged: " + ChatColor.BLUE + Double.toString(price));
		sender.sendMessage(ChatColor.DARK_AQUA + "Your " + ChatColor.BLUE + itemName + ChatColor.DARK_AQUA + " has been identified!");
		return;
	}
}
	

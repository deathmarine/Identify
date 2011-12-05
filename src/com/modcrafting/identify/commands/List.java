package com.modcrafting.identify.commands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.modcrafting.identify.Identify;

public class List {
	Identify plugin;

	public List(Identify identify) {
		this.plugin = identify;
	}
	
	public void showList(Player sender) {
		ItemStack item = sender.getItemInHand();
		String itemName = item.toString();
		sender.sendMessage(ChatColor.DARK_AQUA + "Identify Shop  {Current Item: " + itemName + " }");
		sender.sendMessage(ChatColor.DARK_AQUA + "-----------------------------------------");
		sender.sendMessage(ChatColor.DARK_AQUA + "What would type of item would you like to enchant?");
		sender.sendMessage(ChatColor.GOLD + "For a random enchantment type /identify buy random");
		sender.sendMessage(ChatColor.GOLD + "Use /identify list {item}");
		sender.sendMessage(ChatColor.BLUE + "TOOLS");
		sender.sendMessage(ChatColor.BLUE + "ARMOR");
		sender.sendMessage(ChatColor.BLUE + "WEAPONS");
		return;
	}
	public void toolList(Player sender){
		YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
		int iprice = config.getInt("prices.levelprice", 500);
		String eitemPrice = Integer.toString(iprice);
		ItemStack item = sender.getItemInHand();
		String itemName = item.toString();
		sender.sendMessage(ChatColor.DARK_AQUA + "Identify Shop  {Current Item: " + itemName + " }");
		sender.sendMessage(ChatColor.DARK_AQUA + "-----------------------------------------");
		sender.sendMessage(ChatColor.DARK_AQUA + "Each level costs " + eitemPrice + "!");
		sender.sendMessage(ChatColor.GOLD + "Use /Identify buy {ID#}");
		sender.sendMessage(ChatColor.BLUE + "What enchant would you like to add?");
		sender.sendMessage(ChatColor.BLUE + "ID#4 DIG_SPEED +1");
		sender.sendMessage(ChatColor.BLUE + "ID#5 DURABILITY +1");
		sender.sendMessage(ChatColor.BLUE + "ID#8 LOOT_BONUS_BLOCKS +1");
		sender.sendMessage(ChatColor.BLUE + "ID#16 SILK_TOUCH +1");
		return;		
	}
	public void armorList(Player sender){
		YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
		int iprice = config.getInt("prices.levelprice", 500);
		String eitemPrice = Integer.toString(iprice);
		ItemStack item = sender.getItemInHand();
		String itemName = item.toString();
		sender.sendMessage(ChatColor.DARK_AQUA + "Identify Shop  {Current Item: " + itemName + " }");
		sender.sendMessage(ChatColor.DARK_AQUA + "-----------------------------------------");
		sender.sendMessage(ChatColor.DARK_AQUA + "Each level costs " + eitemPrice + "!");
		sender.sendMessage(ChatColor.GOLD + "Use /Identify buy {ID#}");
		sender.sendMessage(ChatColor.BLUE + "What enchant would you like to add?");
		sender.sendMessage(ChatColor.BLUE + "ID#10 OXYGEN +1");
		sender.sendMessage(ChatColor.BLUE + "ID#11 PROTECTION_ENVIRONMENTAL +1");
		sender.sendMessage(ChatColor.BLUE + "ID#12 PROTECTION_EXPLOSIONS +1");
		sender.sendMessage(ChatColor.BLUE + "ID#13 PROTECTION_FALL +1");
		sender.sendMessage(ChatColor.BLUE + "ID#14 PROTECTION_FIRE +1");
		sender.sendMessage(ChatColor.BLUE + "ID#15 PROTECTION_PROJECTILE +1");
		sender.sendMessage(ChatColor.BLUE + "ID#17 WATER_WORKER +1");
		return;		
	}
	public void weaponList(Player sender){
		YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
		int iprice = config.getInt("prices.levelprice", 500);
		String eitemPrice = Integer.toString(iprice);
		ItemStack item = sender.getItemInHand();
		String itemName = item.toString();
		sender.sendMessage(ChatColor.DARK_AQUA + "Identify Shop  {Current Item: " + itemName + " }");
		sender.sendMessage(ChatColor.DARK_AQUA + "-----------------------------------------");
		sender.sendMessage(ChatColor.DARK_AQUA + "Each level costs " + eitemPrice + "!");
		sender.sendMessage(ChatColor.GOLD + "Use /Identify buy list {ID#}");
		sender.sendMessage(ChatColor.BLUE + "What enchant would you like to add?");
		sender.sendMessage(ChatColor.BLUE + "ID#1 DAMAGE_ALL +1");
		sender.sendMessage(ChatColor.BLUE + "ID#2 DAMAGE_ARTHROPODS +1");
		sender.sendMessage(ChatColor.BLUE + "ID#3 DAMAGE_UNDEAD +1");
		sender.sendMessage(ChatColor.BLUE + "ID#6 FIRE_ASPECT +1");
		sender.sendMessage(ChatColor.BLUE + "ID#7 KNOCKBACK +1");
		sender.sendMessage(ChatColor.BLUE + "ID#9 LOOT_BONUS_MOBS +1");
		return;		
	}

}

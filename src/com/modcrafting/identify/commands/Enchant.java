package com.modcrafting.identify.commands;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
/*
 * This is complete.
 * 
 */
public class Enchant {

		//All Comments for Safe Enchantments
	public static Enchantment enchant(int num){
		switch(num){
			case 1: return Enchantment.DAMAGE_ALL; //Weapons Only
			case 2: return Enchantment.DAMAGE_ARTHROPODS; //Weapons Only
			case 3: return Enchantment.DAMAGE_UNDEAD; //Weapons Only
			case 4: return Enchantment.DIG_SPEED; //Tools except Hoe.
			case 5: return Enchantment.DURABILITY; //Tools except Hoe.
			case 6: return Enchantment.FIRE_ASPECT; //Weapons Only
			case 7: return Enchantment.KNOCKBACK; //Weapons Only
			case 8: return Enchantment.LOOT_BONUS_BLOCKS; //Tools except Hoe.
			case 9: return Enchantment.LOOT_BONUS_MOBS; //Weapons Only
			case 10: return Enchantment.OXYGEN; //Armor Helmet Only
			case 11: return Enchantment.PROTECTION_ENVIRONMENTAL; //All Armor
			case 12: return Enchantment.PROTECTION_EXPLOSIONS; //All Armor
			case 13: return Enchantment.PROTECTION_FALL; //All Armor
			case 14: return Enchantment.PROTECTION_FIRE; //All Armor
			case 15: return Enchantment.PROTECTION_PROJECTILE; //All Armor
			case 16: return Enchantment.SILK_TOUCH; //Tools except Hoe
			case 17: return Enchantment.WATER_WORKER; //Armor Helmet Only
			case 18: return Enchantment.ARROW_DAMAGE;
			case 19: return Enchantment.ARROW_FIRE;
			case 20: return Enchantment.ARROW_KNOCKBACK;
			case 21: return Enchantment.ARROW_INFINITE;
			default: return null;
		}
	}
	public static boolean testEnchantment(ItemStack item){
		if (item.containsEnchantment(enchant(1))) return true;
		if (item.containsEnchantment(enchant(2))) return true;
		if (item.containsEnchantment(enchant(3))) return true;
		if (item.containsEnchantment(enchant(4))) return true;
		if (item.containsEnchantment(enchant(5))) return true;
		if (item.containsEnchantment(enchant(6))) return true;
		if (item.containsEnchantment(enchant(7))) return true;
		if (item.containsEnchantment(enchant(8))) return true;
		if (item.containsEnchantment(enchant(9))) return true;
		if (item.containsEnchantment(enchant(10))) return true;
		if (item.containsEnchantment(enchant(11))) return true;
		if (item.containsEnchantment(enchant(12))) return true;
		if (item.containsEnchantment(enchant(13))) return true;
		if (item.containsEnchantment(enchant(14))) return true;
		if (item.containsEnchantment(enchant(15))) return true;
		if (item.containsEnchantment(enchant(16))) return true;
		if (item.containsEnchantment(enchant(17))) return true;
		if (item.containsEnchantment(enchant(18))) return true;
		if (item.containsEnchantment(enchant(19))) return true;
		if (item.containsEnchantment(enchant(20))) return true;
		if (item.containsEnchantment(enchant(21))) return true;
		return false;
	}
	public static Enchantment enchantName(String num){
		if (num.equalsIgnoreCase("DAMAGE_ALL")) return Enchantment.DAMAGE_ALL;
		if (num.equalsIgnoreCase("DAMAGE_ARTHROPODS")) return Enchantment.DAMAGE_ARTHROPODS;
		if (num.equalsIgnoreCase("DAMAGE_UNDEAD")) return Enchantment.DAMAGE_UNDEAD;
		if (num.equalsIgnoreCase("DIG_SPEED")) return Enchantment.DIG_SPEED;
		if (num.equalsIgnoreCase("DURABILITY")) return Enchantment.DURABILITY;
		if (num.equalsIgnoreCase("FIRE_ASPECT")) return Enchantment.FIRE_ASPECT;
		if (num.equalsIgnoreCase("KNOCKBACK")) return Enchantment.KNOCKBACK;
		if (num.equalsIgnoreCase("LOOT_BONUS_BLOCKS")) return Enchantment.LOOT_BONUS_BLOCKS;
		if (num.equalsIgnoreCase("LOOT_BONUS_MOBS")) return Enchantment.LOOT_BONUS_MOBS;
		if (num.equalsIgnoreCase("OXYGEN")) return Enchantment.OXYGEN;
		if (num.equalsIgnoreCase("PROTECTION_ENVIRONMENTAL")) return Enchantment.PROTECTION_ENVIRONMENTAL;
		if (num.equalsIgnoreCase("PROTECTION_EXPLOSIONS")) return Enchantment.PROTECTION_EXPLOSIONS;
		if (num.equalsIgnoreCase("PROTECTION_FALL")) return Enchantment.PROTECTION_FALL;
		if (num.equalsIgnoreCase("PROTECTION_FIRE")) return Enchantment.PROTECTION_FIRE;
		if (num.equalsIgnoreCase("PROTECTION_PROJECTILE")) return Enchantment.PROTECTION_PROJECTILE;
		if (num.equalsIgnoreCase("SILK_TOUCH")) return Enchantment.SILK_TOUCH;
		if (num.equalsIgnoreCase("WATER_WORKER")) return Enchantment.WATER_WORKER;
		if (num.equalsIgnoreCase("ARROW_DAMAGE")) return Enchantment.ARROW_DAMAGE;
		if (num.equalsIgnoreCase("ARROW_FIRE")) return Enchantment.ARROW_FIRE;
		if (num.equalsIgnoreCase("ARROW_KNOCKBACK")) return Enchantment.ARROW_KNOCKBACK;
		if (num.equalsIgnoreCase("ARROW_INFINITE")) return Enchantment.ARROW_INFINITE;
		return null;
		
	}
}

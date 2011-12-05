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
			default: return Enchantment.DAMAGE_ALL;
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
		return false;
	}
}

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
		return false;
	}
	public static Enchantment enchantName(String num){
		if (num == "DAMAGE_ALL") return enchant(1);
		if (num == "DAMAGE_ARTHROPODS") return enchant(2);
		if (num == "DAMAGE_UNDEAD") return enchant(3);
		if (num == "DIG_SPEED") return enchant(4);
		if (num == "DURABILITY") return enchant(5);
		if (num == "FIRE_ASPECT") return enchant(6);
		if (num == "KNOCKBACK") return enchant(7);
		if (num == "LOOT_BONUS_BLOCKS") return enchant(8);
		if (num == "LOOT_BONUS_MOBS") return enchant(9);
		if (num == "OXYGEN") return enchant(10);
		if (num == "PROTECTION_ENVIRONMENTAL") return enchant(11);
		if (num == "PROTECTION_EXPLOSIONS") return enchant(12);
		if (num == "PROTECTION_FALL") return enchant(13);
		if (num == "PROTECTION_FIRE") return enchant(14);
		if (num == "PROTECTION_PROJECTILE") return enchant(15);
		if (num == "SILK_TOUCH") return enchant(16);
		if (num == "WATER_WORKER") return enchant(17);
		return null;
		
	}
}

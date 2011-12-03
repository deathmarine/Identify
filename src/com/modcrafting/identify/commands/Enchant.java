package com.modcrafting.identify.commands;

import org.bukkit.enchantments.Enchantment;
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
		
}

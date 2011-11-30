package com.modcrafting.identify.commands;

import org.bukkit.enchantments.Enchantment;
/*
 * This is complete.
 * 
 */
public class Enchant {

	public static Enchantment 	DAMAGE_ALL;
	public static Enchantment 	DAMAGE_ARTHROPODS;
	public static Enchantment 	DAMAGE_UNDEAD;
	public static Enchantment 	DIG_SPEED;
	public static Enchantment 	DURABILITY;
	public static Enchantment 	FIRE_ASPECT;
	public static Enchantment 	KNOCKBACK;
	public static Enchantment 	LOOT_BONUS_BLOCKS;
	public static Enchantment 	LOOT_BONUS_MOBS;
	public static Enchantment 	OXYGEN;
	public static Enchantment 	PROTECTION_ENVIRONMENTAL;
	public static Enchantment 	PROTECTION_EXPLOSIONS;
	public static Enchantment 	PROTECTION_FALL;
	public static Enchantment 	PROTECTION_FIRE;
	public static Enchantment 	PROTECTION_PROJECTILE;
	public static Enchantment 	SILK_TOUCH;
	public static Enchantment 	WATER_WORKER;
	
	public static Enchantment enchant(int num){
		switch(num){
			case 1: return DAMAGE_ALL;
			case 2: return DAMAGE_ARTHROPODS;
			case 3: return DAMAGE_UNDEAD;
			case 4: return DIG_SPEED;
			case 5: return DURABILITY;
			case 6: return FIRE_ASPECT;
			case 7: return KNOCKBACK;
			case 8: return LOOT_BONUS_BLOCKS;
			case 9: return LOOT_BONUS_MOBS;
			case 10: return OXYGEN;
			case 11: return PROTECTION_ENVIRONMENTAL;
			case 12: return PROTECTION_EXPLOSIONS;
			case 13: return PROTECTION_FALL;
			case 14: return PROTECTION_FIRE;
			case 15: return PROTECTION_PROJECTILE;
			case 16: return SILK_TOUCH;
			case 17: return WATER_WORKER;
			default: return null;
		}
	}
}

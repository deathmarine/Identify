package com.modcrafting.identify.commands;

import org.bukkit.enchantments.Enchantment;

public class EnchantUtil {
	public static Enchantment getByID(int num){
		return Enchantment.values()[num];
	}
	public static Integer getID(Enchantment e){
		for(int i=0;i<Enchantment.values().length;i++){
			if(Enchantment.values()[i].equals(e)) return i;
		}
		return null;
		
	}
	public static Enchantment enchantName(String num){
		return Enchantment.getByName(num.toUpperCase());
	}
}

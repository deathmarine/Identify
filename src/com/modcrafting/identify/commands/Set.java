package com.modcrafting.identify.commands;

import java.util.logging.Level;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.modcrafting.identify.Identify;

public class Set{
	Identify plugin;
	public Set(Identify identify) {

		this.plugin = identify;
	}
	public void set(String arg1, String arg2, String arg3, String arg4, Player sender){

		YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
		
		/*Build Configuration Commands Completed
		 * Set
		 * levelprice-done
		 * randomprice-done
		 * type-done
		 * 
		 */
		if(arg1.equalsIgnoreCase("set")){
			if(arg2.equalsIgnoreCase("levelprice")){
				String p = arg3;
				if (p == null){
					sender.sendMessage("You must specify a value.");
					return;
				}
				int pvar = Integer.parseInt(p);
				config.set("prices.levelprice", (int) pvar);
				plugin.saveConfig();
				sender.sendMessage("Level Enchant Price Set to " + p + "!");
				Identify.log.log(Level.INFO, "[Identify] Level Enchant Price Set to " + p + "!");
				return;
			}
			if(arg3.equalsIgnoreCase("randomprice")){
				String p = arg3;
				int pvar = Integer.parseInt(p);
				config.set("prices.randomprice", (int) pvar);
				plugin.saveConfig();
				sender.sendMessage("iRandom Enchant Price Set to " + p + "!");
				Identify.log.log(Level.INFO, "[Identify] iRandom Enchant Price Set to " + p + "!");
				return;
			}
			if(arg2.equalsIgnoreCase("type")){
				if(arg3.equalsIgnoreCase("tools")){
					boolean tool = new Boolean(arg4);
					if(tool){
						config.set("enchantTools", (boolean) true);
						sender.sendMessage("Enchant Tools Set True!");
						Identify.log.log(Level.INFO, "[Identify] Enchant Tools Set to True!");
					}else{
						config.set("enchantTools", (boolean) false);
						sender.sendMessage("Enchant Tools Set False!");
						Identify.log.log(Level.INFO, "[Identify] Enchant Tools Set to False!");
					}
					plugin.saveConfig();
					return;
				}
				if(arg3.equalsIgnoreCase("armor")){
					boolean armor = new Boolean(arg4);
					if(armor){
						config.set("enchantArmor", (boolean) true);
						sender.sendMessage("Enchant Armor Set True!");
						Identify.log.log(Level.INFO, "[Identify] Enchant Armor Set to True!");
					}else{
						config.set("enchantArmor", (boolean) false);
						sender.sendMessage("Enchant Armor Set False!");
						Identify.log.log(Level.INFO, "[Identify] Enchant Armor Set to False!");
					}
					plugin.saveConfig();
					return;
					
				}
				sender.sendMessage("/identify set type <tools/armor>");
				return;
			}
			sender.sendMessage("/identify set <levelprice/randomprice/type>");
			return;
		}
		/*
		 * End of Set
		 */
	}

}

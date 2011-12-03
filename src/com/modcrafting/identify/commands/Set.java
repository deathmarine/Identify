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

	public void set(String secondCommand, String argCommand, String argSetting, Player sender){

		YamlConfiguration config = (YamlConfiguration) plugin.getConfig();
		
		/*Build Configuration Commands Completed
		 * Set
		 * levelprice-done
		 * randomprice-done
		 * type-done
		 * 
		 */
			if(secondCommand.equalsIgnoreCase("levelprice")){
				String p = argCommand;
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
			if(secondCommand.equalsIgnoreCase("randomprice")){
				String p = argCommand;
				int pvar = Integer.parseInt(p);
				config.set("prices.randomprice", (int) pvar);
				plugin.saveConfig();
				sender.sendMessage("iRandom Enchant Price Set to " + p + "!");
				Identify.log.log(Level.INFO, "[Identify] iRandom Enchant Price Set to " + p + "!");
				return;
			}
			return;
		}
		/*
		 * End of Set
		 */
	

}

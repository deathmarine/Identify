package com.modcrafting.identify.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.modcrafting.identify.Identify;

public class Set extends IdentifyCommand{
	public Set(Identify identify) {
		super(identify);
		// TODO Auto-generated constructor stub
		}

	public static final Logger log = Logger.getLogger("Minecraft");
	Identify plugin;

	public boolean set(Player sender, String arg1, String arg2, String arg3, String arg4){
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
					return true;
				}
				int pvar = Integer.parseInt(p);
				config.set("prices.levelprice", (int) pvar);
				plugin.saveConfig();
				sender.sendMessage("Level Enchant Price Set to " + p + "!");
				Identify.log.log(Level.INFO, "[Identify] Level Enchant Price Set to " + p + "!");
				return true;
			}
			if(arg3.equalsIgnoreCase("randomprice")){
				String p = arg3;
				if (p == null){
					sender.sendMessage("You must specify a value.");
					return true;
				}
				int pvar = Integer.parseInt(p);
				config.set("prices.randomprice", (int) pvar);
				plugin.saveConfig();
				sender.sendMessage("Random Enchant Price Set to " + p + "!");
				Identify.log.log(Level.INFO, "[Identify] Random Enchant Price Set to " + p + "!");
				return true;
			}
			if(arg2.equalsIgnoreCase("type")){
				if(arg3.equalsIgnoreCase("tools")){
					if(args.length < 3){
						sender.sendMessage("Select True or False!");
						return true;
					}
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
					return true;
				}
				if(arg3.equalsIgnoreCase("armor")){
					if(args.length < 3){
						sender.sendMessage("Select True or False!");
						return true;
					}
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
					return true;
					
				}
				sender.sendMessage("/identify set type <tools/armor>");
				return true;
			}
			sender.sendMessage("/identify set <levelprice/randomprice/type>");
			return true;
		}
		/*
		 * End of Set
		 */
		return false;
		
	}

}

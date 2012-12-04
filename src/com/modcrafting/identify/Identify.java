package com.modcrafting.identify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import com.modcrafting.identify.commands.Buying;
import com.modcrafting.identify.commands.Enchant;
import com.modcrafting.identify.commands.Help;
import com.modcrafting.identify.commands.IdentifyCommand;
import com.modcrafting.identify.commands.List;

public class Identify extends JavaPlugin{
	public Enchant enchantments = new Enchant();
	public net.milkbowl.vault.economy.Economy economy;
	public Buying buy;
	public Help help;
	public List list;
	public FileConfiguration ddConfig = new YamlConfiguration();
	
	public void onEnable() {
		if(getDiabloDrops()==null){
			this.getLogger().warning("Shutting down Identify. Could not find DiabloDrops.");
			this.setEnabled(false);
			return;
		}
		if(!setupEconomy()){
			this.getLogger().warning("Shutting down Identify. Could not find Vault.");
			this.setEnabled(false);
			return;
			
		}
		list = new List(this);
		help = new Help(this);
		buy = new Buying(this);
		this.getDataFolder().mkdir();
		writeDefault("config.yml");
		writeDefault("diablodrops.yml");
		try {
			ddConfig.load(new File(this.getDataFolder(),"diablodrops.yml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		loadCommands();
		
	}
	public void loadCommands(){
		getCommand("identify").setExecutor(new IdentifyCommand(this));
	}
	public boolean setupEconomy(){
		RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}
			return (economy != null);
	}
	public com.modcrafting.diablodrops.DiabloDrops getDiabloDrops() {
		Plugin plugin = getServer().getPluginManager().getPlugin("DiabloDrops");

		if (plugin == null || !(plugin instanceof com.modcrafting.diablodrops.DiabloDrops)) {
			return null;
		}

		return (com.modcrafting.diablodrops.DiabloDrops) plugin;
	}
	 public void writeDefault(String name)
	    {
	        File actual = new File(this.getDataFolder(), name);
	        if (!actual.exists())
	        {
	            try
	            {
	                InputStream input = this.getClass().getResourceAsStream(
	                        "/"+name);
	                FileOutputStream output = new FileOutputStream(actual);
	                byte[] buf = new byte[8192];
	                int length = 0;
	                while ((length = input.read(buf)) > 0)
	                {
	                    output.write(buf, 0, length);
	                }
	                output.close();
	                input.close();
	            }
	            catch (Exception e)
	            {
	            	e.printStackTrace();
	            }
	        }
	    }
}


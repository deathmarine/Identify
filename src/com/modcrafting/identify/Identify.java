package com.modcrafting.identify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.modcrafting.identify.commands.Buying;
import com.modcrafting.identify.commands.Enchant;
import com.modcrafting.identify.commands.Help;
import com.modcrafting.identify.commands.IdentifyCommand;
import com.modcrafting.identify.commands.Set;

public class Identify extends JavaPlugin{
	public final static Logger log = Logger.getLogger("Minecraft");
	public String maindir = "plugins/Identify/";
	public boolean random;
	public Enchant enchantments;
	public net.milkbowl.vault.permission.Permission permission = null;
	public net.milkbowl.vault.economy.Economy economy = null;
	public Buying buy = new Buying(this);
	public Help help = new Help(this);
	public Set set = new Set(this);
	public void onDisable() {
		System.out.println("UltraBan disabled.");
	}
	
	protected void createDefaultConfiguration(String name) {
		File actual = new File(getDataFolder(), name);
		if (!actual.exists()) {

			InputStream input =
				this.getClass().getResourceAsStream("/" + name);
			if (input != null) {
				FileOutputStream output = null;

				try {
					output = new FileOutputStream(actual);
					byte[] buf = new byte[8192];
					int length = 0;
					while ((length = input.read(buf)) > 0) {
						output.write(buf, 0, length);
					}

					log.log(Level.INFO, getDescription().getName()
							+ ": Default configuration file written: " + name);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (input != null)
							input.close();
					} catch (IOException e) {}

					try {
						if (output != null)
							output.close();
					} catch (IOException e) {}
				}
			}
		}
	}
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		new File(maindir).mkdir();
		createDefaultConfiguration("config.yml");
		YamlConfiguration Config = (YamlConfiguration) getConfig();
		random = Config.getBoolean("random", false);
		loadCommands();
		log.log(Level.INFO,"[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " is enabled!" );
		
	}
	public void loadCommands(){
		getCommand("identify").setExecutor(new IdentifyCommand(this));
		return;
	}
	public Boolean setupPermissions()
    {
        RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
	public boolean setupEconomy(){
		RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}
			return (economy != null);
	}
}


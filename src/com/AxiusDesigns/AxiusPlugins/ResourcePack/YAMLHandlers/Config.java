package com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.AxiusDesigns.AxiusPlugins.ResourcePack.ResourcePack;

public class Config {

	ResourcePack plugin;
	
	HashMap<String, String> messages;
	
	public HashMap<String, String> configData;
	
	public Config(ResourcePack instance) {
		plugin = instance;
		this.configData = new HashMap<String, String>();
		messages = instance.messageData;
	}
	
	public HashMap<String, String> getMessageData() {
		File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "AxiusPlugins" + File.separator + "ResourcePack" + File.separator + "config.yml");
		if(!f.exists()) {
			saveMessages();
			try {
				f.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return loadMessages();
	}
	
	public HashMap<String, String> loadMessages() {
		File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "AxiusPlugins" + File.separator + "ResourcePack" + File.separator + "config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		for(String message : config.getConfigurationSection("").getKeys(true)) this.configData.put(message, config.getString(message));
		return this.configData;
	}
	
	private void saveMessages() {
		setMessage("Method", "CHAT", false);
		setMessage("URL.Default", "https://www.dropbox.com/s/wu5psei4yca6st4/0.9.1.zip?dl=0", false);
	}
	
	private void setMessage(String key, String value, boolean override) {
		File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "AxiusPlugins" + File.separator + "ResourcePack" + File.separator + "config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		if(!config.isSet(key)||override) {
			config.set(key, value);
			try {
				config.save(f);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addWorld(Player p, String world, String url) {
		File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "AxiusPlugins" + File.separator + "ResourcePack" + File.separator + "config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		  config.set("URL." + world, url);
	      try
	      {
	        config.save(f);
		    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("Add.Success")));
	      }
	      catch (IOException e)
	      {
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("Error.Add")));
	        e.printStackTrace();
	      }
	  }
}

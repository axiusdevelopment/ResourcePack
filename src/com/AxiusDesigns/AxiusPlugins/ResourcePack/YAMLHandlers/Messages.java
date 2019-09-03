package com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.AxiusDesigns.AxiusPlugins.ResourcePack.ResourcePack;


public class Messages {
	
	ResourcePack plugin;
	public HashMap<String, String> messageData;
	
	public Messages(ResourcePack instance) {
		plugin = instance;
		this.messageData = new HashMap<String, String>();
	}
	
	public HashMap<String, String> getMessageData()
	  {
		File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "AxiusPlugins" + File.separator + "ResourcePack" + File.separator + "messages.yml");
        saveMessages();
	    if (!f.exists()) {
	      try
	      {
	        f.createNewFile();
	      }
	      catch (IOException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    return loadMessages();
	  }

	public HashMap<String, String> loadMessages()
	  {
		File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "AxiusPlugins" + File.separator + "ResourcePack" + File.separator + "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	    for (String message : config.getConfigurationSection("").getKeys(true)) {
	      this.messageData.put(message, config.getString(message));
	    }
	    return this.messageData;
	  }
	  
	  private void saveMessages()
	  {
		  setMessage("Method.CHAT.Message.Begin", "&c&lResourcePack&7 Click ");
		  setMessage("Method.CHAT.Message.End", "&7 to receive the resource pack.");
		  setMessage("Method.CHAT.Link.Text", "&c&lHERE");
		  setMessage("Method.CHAT.Link.Hover", "&7This link will take you to another application.");
		  
		  setMessage("Permission.Reload", "&c&lResourcePack&7 You have to be &c&lADMIN&7 or above to use this command!");
		  setMessage("Permission.Send", "&c&lResourcePack&7 You have to be &c&lMODERATOR&7 or above to use this command!");
		  setMessage("Permission.Add", "&c&lResourcePack&7 You have to be &c&lADMIN&7 or above to use this command!");
		  setMessage("Permission.Info", "&c&lResourcePack&7 You have to be &c&lMODERATOR&7 or above to use this command!");
		  
		  setMessage("Reload.Config", "&c&lResourcePack&7 Successfully reloaded &cConfig.yml&7.");
		  setMessage("Reload.Messages", "&c&lResourcePack&7 Successfully reloaded &cMessages.yml&7.");
		  setMessage("Reload.Both", "&c&lResourcePack&7 Successfully reloaded &cConfig.yml&7 and &cMessages.yml&7.");
		  
		  setMessage("Syntax.Reload", "&c&lResourcePack&7 Usage: /pack-reload <both|config|messages>");
		  setMessage("Syntax.Send", "&c&lResourcePack&7 Usage: /pack-send <player>");
		  setMessage("Syntax.Add", "&c&lResourcePack&7 Usage: /pack-add <world-name> <URL>");
		  
		  setMessage("Error.Player", "&c&lResourcePack&7 That player couldn't be located.");
		  setMessage("Error.Add", "&c&lResourcePack&7 Error while adding new URL. Check console for stack.");
		  
		  setMessage("Pack.Received", "&c&lResourcePack&7 You have been sent the resource pack by %sender%.");
		  setMessage("Pack.Sent", "&c&lResourcePack&7 You have sent the resource pack to %player%.");
		  
		  setMessage("Add.Success", "&c&lResourcePack&7 Successfully added/updated world URL!");
		  
		  setMessage("Info.Header", "&7&m-------+&c&l ResourcePack &7&m-------+");
		  setMessage("Info.World", "&c&lWORLD&8 - &7%WORLD%");
		  setMessage("Info.RP", "&c&lRESOURCEPACK&8 - &7%PACK%");
		  setMessage("Info.Players", "&c&lPLAYERS&8 - &7%WORLD_PLAYERS% / %SERVER_PLAYERS% / %SERVER_MAX%");
	  }
	  
	  private void setMessage(String name, String message)
	  {
		  File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "AxiusPlugins" + File.separator + "ResourcePack" + File.separator + "messages.yml");
	      FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		   if (!config.isSet(name))
		    {
		      config.set(name, message);
		      try
		      {
		        config.save(f);
		      }
		      catch (IOException e)
		      {
		        e.printStackTrace();
		      }
		    }
	  }
}
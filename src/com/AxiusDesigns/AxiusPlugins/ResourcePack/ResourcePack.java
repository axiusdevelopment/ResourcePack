package com.AxiusDesigns.AxiusPlugins.ResourcePack;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import com.AxiusDesigns.AxiusPlugins.ResourcePack.Commands.AddCommand;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.Commands.InfoCommand;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.Commands.PackCommand;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.Commands.ReloadCommand;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.Commands.SendCommand;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.Events.JoinEvent;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers.Config;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers.Messages;

public class ResourcePack extends JavaPlugin {
	
	public String prefix = "[ResourcePack] ";
	
	public Messages messages;
	public Config config;
	public HashMap<String, String> messageData = new HashMap<String, String>();
	public HashMap<String, String> configData = new HashMap<String, String>();
	
	public void onEnable() {
		System.out.print(prefix + "Enabling plugin.");
		
		//FILES
		System.out.print("- Checking files (1/2)");
		File data = new File(this.getDataFolder().getParentFile() + File.separator + "AxiusPlugins");
		if(!data.exists()) {
			System.out.print("- File not found, creating (1/2)");
			data.mkdir();
		}
		else System.out.print("- File found (1/2)");
		
		System.out.print("- Checking files (2/2)");
		File rph = new File(data + File.separator + "ResourcePack");
		if(!rph.exists()) {
			System.out.print("- File not found, creating (2/2)");
			rph.mkdir();
		}
		else System.out.print("- File found (2/2)");
		
		//CONF/MESSAGES
		System.out.print("- Loading messages.yml");
		this.messages = new Messages(this);
		this.messageData = this.messages.getMessageData();
		
		System.out.print("- Loading config.yml");
		this.config = new Config(this);
		this.configData = this.config.getMessageData();
		
		//UPDATES
		System.out.print("- Checking for updates.");
		System.out.print(getUpdate());
		if(getUpdate() != null) {
			float f = Float.parseFloat(getUpdate());
			if(f > Float.parseFloat(getDescription().getVersion())) {
				System.out.print("- Update found (" + f + ").");
				getServer().getPluginManager().registerEvents(new JoinEvent(f, Float.parseFloat(getDescription().getVersion())), this);
			}
		}
		
		//COMMANDS
		getCommand("Pack").setExecutor(new PackCommand(this));
		getCommand("Pack-Reload").setExecutor(new ReloadCommand(config, messages, this));
		getCommand("Pack-Send").setExecutor(new SendCommand(config, messages, this));
		getCommand("Pack-Add").setExecutor(new AddCommand(config, messages, this));
		getCommand("Pack-Info").setExecutor(new InfoCommand(config, messages, this));
		
		System.out.print(prefix + "Plugin enabled.");

		//FORCE
		if(configData.get("Method").equalsIgnoreCase("FORCE")) {
			System.out.print("- \"FORCE\" method selected, enabling force resourcepack");
			//getServer().getPluginManager().registerEvents(new ForcePack(this), this);
		}
	}
	
	public String getUpdate() {
		String v = "";
		try {
			HttpURLConnection con = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php").openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.getOutputStream().write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=43639").getBytes("UTF-8"));
			String vv = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
			v = vv;
		}
		catch (Exception ex) {
			System.out.print("- Failed to check for an update on spigot.");
		}
		return v;
	}
	
}

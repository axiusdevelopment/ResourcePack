package com.AxiusDesigns.AxiusPlugins.ResourcePack.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.AxiusDesigns.AxiusPlugins.ResourcePack.ResourcePack;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers.Config;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers.Messages;

public class InfoCommand implements CommandExecutor {

	Config config;
	Messages messages;
	ResourcePack main;

	public InfoCommand(Config config, Messages messages, ResourcePack main) {
		this.config = config;
		this.messages = messages;
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args) {
		if(sndr instanceof Player) {
			if(!sndr.hasPermission("resourcepack.info")) {
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Permission.Send")));
				return true;
			}
			else {
				
				Player p = (Player) sndr;
				
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Info.Header")));
				
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Info.World").replaceAll("%WORLD%", ((Player) sndr).getWorld().getName())));
				
				String URL = "";
				if(this.main.configData.containsKey("URL." + p.getWorld().getName())) URL = this.main.configData.get("URL." + p.getWorld().getName());
				else URL = this.main.configData.get("URL.Default");
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Info.RP").replaceAll("%PACK%", URL)));
				
				int playersInWorld = p.getWorld().getPlayers().size();
				int playersOnServer = p.getServer().getOnlinePlayers().size();
				int serverMaxPlayers = p.getServer().getMaxPlayers();
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Info.Players").replaceAll("%WORLD_PLAYERS%", playersInWorld + "").replaceAll("%SERVER_PLAYERS%", playersOnServer + "").replaceAll("%SERVER_MAX%", serverMaxPlayers + "")));
				return true;
			}
		}
		else {
			System.out.print("YOU MUST BE A PLAYER TO USE THE RESOURCE PACK!");
			return true;
		}
	}

}

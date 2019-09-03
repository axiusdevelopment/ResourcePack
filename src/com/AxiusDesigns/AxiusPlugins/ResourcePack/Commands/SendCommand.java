package com.AxiusDesigns.AxiusPlugins.ResourcePack.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.AxiusDesigns.AxiusPlugins.ResourcePack.ResourcePack;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers.Config;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers.Messages;

public class SendCommand implements CommandExecutor {

	Config config;
	Messages messages;
	ResourcePack main;
	
	public SendCommand(Config config, Messages messages, ResourcePack main) {
		this.config = config;
		this.messages = messages;
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args) {
		if(!sndr.hasPermission("resourcepack.send")) {
			sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Permission.Send")));
			return true;
		}
		else
		{
			if(args.length < 1) sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Syntax.Send")));
			else
			{
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Error.Player")));
				else {
					target.chat("/pack");
					target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Pack.Received").replaceAll("%sender%", sndr.getName())));
					sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Pack.Sent").replaceAll("%player%", target.getName())));
				}
			}
			return true;
		}
	}

}
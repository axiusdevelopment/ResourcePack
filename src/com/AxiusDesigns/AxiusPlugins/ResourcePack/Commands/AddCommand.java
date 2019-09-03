package com.AxiusDesigns.AxiusPlugins.ResourcePack.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.AxiusDesigns.AxiusPlugins.ResourcePack.ResourcePack;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers.Config;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers.Messages;

public class AddCommand implements CommandExecutor {

	Config config;
	Messages messages;
	ResourcePack main;
	
	public AddCommand(Config config, Messages messages, ResourcePack main) {
		this.config = config;
		this.messages = messages;
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args) {
		if(!sndr.hasPermission("resourcepack.add")) {
			sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Permission.Add")));
			return true;
		}
		else
		{
			if(args.length < 2) {
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Syntax.Add")));
				return true;
			}
			else
			{
				if(args[1].contains(".")) {
					config.addWorld((Player) sndr, args[0], args[1]);
					return true;
				}
				else
				{
					sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Error.Add")));
					System.out.print(String.format("[%s] Error when adding URL, Link definined is not a URL and thus wouldn't work properly!", main.getDescription().getPrefix()));
					return true;
				}
			}
		}
	}

}
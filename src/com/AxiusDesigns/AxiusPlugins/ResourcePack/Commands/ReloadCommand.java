package com.AxiusDesigns.AxiusPlugins.ResourcePack.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.AxiusDesigns.AxiusPlugins.ResourcePack.ResourcePack;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers.Config;
import com.AxiusDesigns.AxiusPlugins.ResourcePack.YAMLHandlers.Messages;

public class ReloadCommand implements CommandExecutor {

	Messages messages;
	Config config;
	ResourcePack plugin;
	
	public ReloadCommand(Config config, Messages messages, ResourcePack main) {
		this.config = config;
		this.messages = messages;
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args) {
		String perm = "resourcepack.reload";
		String noPerm = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Permission.Reload"));
		if(!sndr.hasPermission(perm)) {
			sndr.sendMessage(noPerm);
			return true;
		}
		else
		{
			if(args.length < 1) {
				String reloadSyntax = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Syntax.Reload"));
				sndr.sendMessage(reloadSyntax);
				return true;
			}
			else
			switch(args[0].toLowerCase()) {
			default:
				String reloadSyntax = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Syntax.Reload"));
				sndr.sendMessage(reloadSyntax);
				break;
			case "both":
				String bothmsg = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Reload.Both"));
				messages.loadMessages();
				config.loadMessages();
				sndr.sendMessage(bothmsg);
				break;
				
			case "config":
				String configmsg = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Reload.Config"));
				config.loadMessages();
				sndr.sendMessage(configmsg);
				break;
				
			case "messages":
				String messagesmsg = ChatColor.translateAlternateColorCodes('&', this.messages.messageData.get("Reload.Messages"));
				messages.loadMessages();
				sndr.sendMessage(messagesmsg);
				break;
			}
			return true;
		}
	}

}
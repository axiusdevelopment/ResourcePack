package com.AxiusDesigns.AxiusPlugins.ResourcePack.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.AxiusDesigns.AxiusPlugins.ResourcePack.ResourcePack;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class PackCommand implements CommandExecutor {

	private ResourcePack plugin;
	String resourcePack;
	String resourcePack2;
	String hereText;
	String hereHover;
	String url = "";
	
	public PackCommand(ResourcePack main) {
		plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] arg3) {
		if(sndr instanceof Player) {
			Player p = Bukkit.getPlayer(sndr.getName());
			if(this.plugin.configData.get("Method").equalsIgnoreCase("CHAT")) {
				showClick(p);
			}
			else
			{
				if(this.plugin.configData.get("Method").equalsIgnoreCase("PROMPT")/*||this.plugin.configData.get("Method").equalsIgnoreCase("FORCE")*/) {
					
					String url = "";
					if(this.plugin.configData.containsKey("URL." + p.getWorld().getName())) url = this.plugin.configData.get("URL." + p.getWorld().getName());
					else url = this.plugin.configData.get("URL.Default");
					p.setResourcePack(url);
				}
				else
				{
					System.out.print("The \"Method\" field in the Config.YML doesn't have an applicable method type, types: PROMPT, CHAT");
					System.out.print("Using default \"CHAT\" Method.");
					showClick(p);
				}
			}
			
			return true;
		}
		else
		{
			System.out.print("YOU MUST BE A PLAYER TO USE THE RESOURCE PACK!");
			return true;
		}
	}

	public void showClick(Player p) {
		resourcePack = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("Method.CHAT.Message.Begin"));
		resourcePack2 = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("Method.CHAT.Message.End"));
		hereText = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("Method.CHAT.Link.Text"));
		hereHover = ChatColor.translateAlternateColorCodes('&', this.plugin.messageData.get("Method.CHAT.Link.Hover"));

		if(this.plugin.configData.containsKey("URL." + p.getWorld().getName())) url = this.plugin.configData.get("URL." + p.getWorld().getName());
		else url = this.plugin.configData.get("URL.Default");
		TextComponent here = new TextComponent(hereText);
		here.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
		here.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hereHover).create()));
		TextComponent a = new TextComponent(resourcePack);
		a.addExtra(here);
		a.addExtra(resourcePack2);
		p.spigot().sendMessage(a);
	}

}
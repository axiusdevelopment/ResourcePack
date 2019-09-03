package com.AxiusDesigns.AxiusPlugins.ResourcePack.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class JoinEvent implements Listener {

	float current;
	float update;
	
	public JoinEvent(float f, float currentVer) {
		current = currentVer;
		update = f;
	}
	
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission("resourcepack.viewUpdates")) p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lResourcePack&7 New version available: &c" + update + "&7. Current version:&c " + current + "&7."));
	}

}

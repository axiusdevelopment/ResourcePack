package com.AxiusDesigns.AxiusPlugins.ResourcePack.Events;

import com.AxiusDesigns.AxiusPlugins.ResourcePack.ResourcePack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class ForcePack implements Listener {

    private final ResourcePack plugin;

    public ForcePack(ResourcePack instance) {
        this.plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        String url = "";
        if(this.plugin.configData.containsKey("URL." + e.getPlayer().getWorld().getName())) url = this.plugin.configData.get("URL." + e.getPlayer().getWorld().getName());
        else url = this.plugin.configData.get("URL.Default");
        e.getPlayer().setResourcePack(url);
    }

    public void onReject(PlayerResourcePackStatusEvent e) {
        if(!e.getStatus().equals(PlayerResourcePackStatusEvent.Status.ACCEPTED)) {

            String url = "";
            if(this.plugin.configData.containsKey("URL." + e.getPlayer().getWorld().getName())) url = this.plugin.configData.get("URL." + e.getPlayer().getWorld().getName());
            else url = this.plugin.configData.get("URL.Default");
            e.getPlayer().setResourcePack(url);
        }
    }

}

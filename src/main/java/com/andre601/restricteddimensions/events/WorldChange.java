package com.andre601.restricteddimensions.events;

import com.andre601.restricteddimensions.RestrictedDimensions;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class WorldChange implements Listener {

    private final RestrictedDimensions plugin;

    public WorldChange(RestrictedDimensions plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerTeleport(PlayerTeleportEvent event){

        if(event.getTo() == null)
            return;
        
        Player player = event.getPlayer();
        World current = event.getFrom().getWorld();
        World target = event.getTo().getWorld();

        if(target == null)
            return;
        
        if(current == target)
            return;
        
        if(player.hasPermission("restricteddimensions.bypass") || player.hasPermission("restricteddimensions.enter." + target.getName()))
            return;

        if(plugin.getConfig().getBoolean("LogDeniedAccess", true))
            plugin.send("§cPlayer %s was denied access to the dimension %s", player.getName(), target.getName());
        
        String msg = plugin.getConfig().getString("NoPermission");
        if(msg == null){
            player.sendMessage(String.format("§cYou don't have permission to enter the dimension %s", target.getName()));
            return;
        }
        
        player.sendMessage(ChatColor.translateAlternateColorCodes(
                '&',
                msg.replace("%dimension%", target.getName())
        ));
        event.setCancelled(true);
    }

}

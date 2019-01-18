package com.andre601.restrictednether.events;

import com.andre601.restrictednether.RestrictedNether;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class WorldChange implements Listener {

    private RestrictedNether plugin;

    public WorldChange(RestrictedNether plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event){

        // We getting the player, the world he comes from and the world he goes to.
        Player player = event.getPlayer();
        World current = event.getFrom().getWorld();
        World target = event.getTo().getWorld();

        // We check, if the target world is the current one, if the dimension is not nether or the player has perms.
        if(current == target) return;
        if(target.getEnvironment() != World.Environment.NETHER) return;
        if(player.hasPermission("restrictednether.allow")) return;

        // Getting the "You don't have permission for ..." message in the config.yml
        player.sendMessage(ChatColor.translateAlternateColorCodes(
                '&',
                plugin.getConfig().getString("NoPermission")
        ));
        event.setCancelled(true);
    }

}

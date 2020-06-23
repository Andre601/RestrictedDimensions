package com.andre601.restricteddimensions.events;

import com.andre601.restricteddimensions.RestrictedDimensions;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class WorldAlter implements Listener{
    
    private final RestrictedDimensions plugin;
    
    public WorldAlter(RestrictedDimensions plugin){
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onWorldDelete(WorldUnloadEvent event){
        World world = event.getWorld();
        
        plugin.getServer().getPluginManager().removePermission(RestrictedDimensions.BASE_PERM + world.getName());
    }
    
    @EventHandler
    public void onWorldLoad(WorldLoadEvent event){
        World world = event.getWorld();
        Permission permission = new Permission(RestrictedDimensions.BASE_PERM + world.getName(), PermissionDefault.TRUE);
    
        plugin.getServer().getPluginManager().addPermission(permission);
    }
}

package com.andre601.restricteddimensions.hooks;

import com.andre601.restricteddimensions.RestrictedDimensions;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceholderAPIHook extends PlaceholderExpansion{
    private final RestrictedDimensions plugin;
    
    public PlaceholderAPIHook(RestrictedDimensions plugin){
        this.plugin = plugin;
    }
    
    @Override
    public String getIdentifier(){
        return plugin.getName().toLowerCase();
    }
    
    @Override
    public String getAuthor(){
        return String.join(", ", plugin.getDescription().getAuthors());
    }
    
    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }
    
    @Override
    public String onPlaceholderRequest(Player player, String identifier){
        
        if(identifier.toLowerCase().startsWith("has_access_")){
            identifier = identifier.toLowerCase().replace("has_access_", "");
            if(identifier.isEmpty())
                return null;
            
            return player.hasPermission("restricteddimensions.bypass") ? PlaceholderAPIPlugin.booleanTrue() :
                   player.hasPermission("restricteddimensions.enter." + identifier) ? PlaceholderAPIPlugin.booleanTrue() :
                           PlaceholderAPIPlugin.booleanFalse();
        }
        
        return null;
    }
}

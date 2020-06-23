package com.andre601.restricteddimensions;

import com.andre601.restricteddimensions.events.WorldAlter;
import com.andre601.restricteddimensions.events.WorldChange;
import com.andre601.restricteddimensions.hooks.PlaceholderAPIHook;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RestrictedDimensions extends JavaPlugin {

    public static final String BASE_PERM = "restricteddimensions.enter.";
    
    @Override
    public void onEnable(){
        saveDefaultConfig();
        PluginManager pluginManager = Bukkit.getPluginManager();

        //  We don't send the banner, if the value is false (not true)
        if(getConfig().getBoolean("ShowBanner"))
            sendBanner();

        send("Enabling %s v%s...", getName(), getDescription().getVersion());
        
        if(pluginManager.isPluginEnabled("PlaceholderAPI")){
            send("PlaceholderAPI found! Hooking into it...");
            new PlaceholderAPIHook(this).register();
        }
        
        send("Loading events...");
        pluginManager.registerEvents(new WorldChange(this), this);
        pluginManager.registerEvents(new WorldAlter(this), this);

        loadPermissions(pluginManager);
        
        send("§aSuccessfully enabled %s v%s!", getName(), getDescription().getVersion());
    }
    
    public void send(String msg, Object... args){
        ConsoleCommandSender sender = getServer().getConsoleSender();
        
        sender.sendMessage("§7[" + getName() + "] " + String.format(msg, args));
    }

    //  I mean a bit advertising should be done right? Can be disabled in config tho. ;P
    private void sendBanner(){
    
        send("");
        send(" §c_____   §4_____");
        send("§c|  __ \\ §4|  __ \\");
        send("§c| |__) |§4| |  | |");
        send("§c|  _  / §4| |  | |");
        send("§c| | \\ \\ §4| |__| |");
        send("§c|_|  \\_\\§4|_____/");
        send("");
    }
    
    private void loadPermissions(PluginManager manager){
        for(World world : getServer().getWorlds()){
            Permission permission = new Permission(BASE_PERM + world.getName(), PermissionDefault.TRUE);
            
            manager.addPermission(permission);
        }
    }

}

package com.andre601.restrictednether;

import com.andre601.restrictednether.events.WorldChange;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RestrictedNether extends JavaPlugin {

    @Override
    public void onEnable(){
        saveDefaultConfig();
        PluginManager pluginManager = Bukkit.getPluginManager();

        //  We don't send the banner, if the value is false (not true)
        if(getConfig().getBoolean("ShowBanner"))
            sendBanner();

        //  Actually loading the events here... Or just the one.
        getLogger().info("Register Events...");
        pluginManager.registerEvents(new WorldChange(this), this);

        getLogger().info("RestrictedNether v" + getDescription().getVersion() + " by Andre_601 enabled!");
    }

    //  I mean a bit advertising should be done right? Can be disabled in config tho. ;P
    private void sendBanner(){
        System.out.println("§c  _____    §4_   _ ");
        System.out.println("§c |  __ \\  §4| \\ | |");
        System.out.println("§c | |__) | §4|  \\| |");
        System.out.println("§c |  _  /  §4| . ` |");
        System.out.println("§c | | \\ \\  §4| |\\  |");
        System.out.println("§c |_|  \\_\\ §4|_| \\_|");
        getLogger().info("Starting RestrictedNether...");
    }

}

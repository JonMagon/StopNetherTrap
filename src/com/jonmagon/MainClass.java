package com.jonmagon;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MainClass extends JavaPlugin implements Listener {

    private static Plugin instance;
    private FileConfiguration config;

    public void onEnable() {

        instance = this;

        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        config = getConfig();

        new Looper(config).runTaskTimer(this, 20L, 20);

        this.getCommand("stopnt").setExecutor(new PluginCommand());
    }

    public static Plugin getInstance() {

        return instance;
    }
}

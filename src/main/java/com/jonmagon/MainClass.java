package com.jonmagon;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;

public class MainClass extends JavaPlugin implements Listener {

    private static Plugin instance;
    private BukkitTask task;

    public void onEnable() {

        instance = this;

        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        Util.config = getConfig();

        task = new Looper().runTaskTimer(this, 20L, 20);

        this.getCommand("stopnt").setExecutor(new PluginCommand());

        getLogger().info("Enabled");
    }

    public void onDisable() {

        task.cancel();

        getLogger().info("Disabled");
    }

    public static Plugin getInstance() {

        return instance;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        Util.playersInPortal.remove(e.getPlayer().getUniqueId());
    }
}

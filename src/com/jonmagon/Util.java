package com.jonmagon;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Util {

    public static FileConfiguration config;

    public static HashMap<Player, Integer> playersInPortal = new HashMap();

    public static String getMessage(String msg) {

        return getMessage(msg, 0);
    }

    public static String getMessage(String msg, Integer time) {

        return ChatColor.translateAlternateColorCodes('&', msg.replaceAll("%s", time.toString()));
    }
}
